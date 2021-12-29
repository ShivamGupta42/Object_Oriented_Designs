package designs.multithreading.DataStructure;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// API Rate limiting mechanism 
// Client side rate limiting
// Opposite of Server side load balancing
public class TokenBucketFilter {

	private long MAX_TOKENS = 0;
	private long availTokens = 0;
	private long lastRequest = System.currentTimeMillis();
	private long tokenDurationPeriod = 0;
	private boolean refillIntervally = true;
	private Object tokenLock = new Object();

	public TokenBucketFilter(long maxTokens, long tokenDurationSeconds) {
		MAX_TOKENS = maxTokens;
		this.tokenDurationPeriod = tokenDurationSeconds;
	}

	public void setRefillTypeGreedy() {
		refillIntervally = false;
	}

	void tryConsume() throws InterruptedException {

		synchronized (tokenLock) {
			refillGreedily();
			while (availTokens == 0) {
				//tokenLock.wait();//Blocking
			    return ; //Non-blocking
			}
			
			/*
			 Do useful work 
			*/
			
			tokenLock.notifyAll();
			availTokens--;
			lastRequest = System.currentTimeMillis();
			System.out.println("Granting token to thread :" + Thread.currentThread().getName() + " at "
					+ System.currentTimeMillis() + "\nTokens left :" + availTokens);
		}

	}

	void refillIntervally() throws InterruptedException {

		synchronized (tokenLock) {
			long elapsedTime = (System.currentTimeMillis() - lastRequest) / 1000;

			if (elapsedTime >= tokenDurationPeriod) {
				availTokens = MAX_TOKENS;
			}
		}

	}

	synchronized void refillGreedily() throws InterruptedException {
		synchronized (tokenLock) {
			long elapsedTime = (System.currentTimeMillis() - lastRequest) / 1000;

			if (elapsedTime >= tokenDurationPeriod) {
				availTokens = MAX_TOKENS;
			} else {
				availTokens += Math.min(MAX_TOKENS, elapsedTime * (MAX_TOKENS / tokenDurationPeriod));
			}
		}

	}

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		TokenBucketFilter t = new TokenBucketFilter(5, 2);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//es.execute(new Filler(t));

		for (int i = 0; i < 10; i++) {
			es.execute(new TokenUser(t));
		}

		es.shutdown();

	}

	static class TokenUser implements Runnable {
		private TokenBucketFilter t;

		public TokenUser(TokenBucketFilter t) {
			this.t = t;
		}

		@Override
		public void run() {
			try {
				t.tryConsume();
			} catch (InterruptedException e) {
				System.out.println("Error in " + Thread.currentThread().getName());
				e.printStackTrace();
			}

		}

	}

	static class Filler implements Runnable {

		private TokenBucketFilter t;

		public Filler(TokenBucketFilter t) {
			this.t = t;
		}

		@Override
		public void run() {
			while (true) {
				try {
					t.refillGreedily();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
