package designs.multithreading.DataStructure;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TokenBucketFilterFIFO {

	private long MAX_TOKENS = 0;
	private long availTokens = 0;
	private long lastRequest = System.currentTimeMillis();
	private long tokenDurationPeriod = 0;
	private boolean refillIntervally = true;
	private Object tokenLock = new Object();
	private ArrayBlockingQueue<Boolean> queue;

	public TokenBucketFilterFIFO(long maxTokens, long tokenDurationSeconds) {
		MAX_TOKENS = maxTokens;
		this.tokenDurationPeriod = tokenDurationSeconds;
		queue = new ArrayBlockingQueue<Boolean>(Long.valueOf(MAX_TOKENS).intValue());
	}

	public void setRefillTypeGreedy() {
		refillIntervally = false;
	}

	void tryConsume() throws InterruptedException {

		synchronized (tokenLock) {
			refillIntervally();
			lastRequest = System.currentTimeMillis();
		}

		queue.take();
		System.out.println("Granting token to thread :" + Thread.currentThread().getName() + " at "
				+ System.currentTimeMillis() + "\nTokens left :" + availTokens);

	}

	void refillIntervally() throws InterruptedException {
		long elapsedTime = (System.currentTimeMillis() - lastRequest) / 1000;

		if (elapsedTime >= tokenDurationPeriod) {
			while (queue.size() < MAX_TOKENS) {
				queue.add(true);
			}
		}

	}

	synchronized void refillGreedily() throws InterruptedException {
		long elapsedTime = (System.currentTimeMillis() - lastRequest) / 1000;

		if (elapsedTime >= tokenDurationPeriod) {
			while (queue.size() < MAX_TOKENS) {
				queue.add(true);
			}
		} else {
			availTokens += Math.min(MAX_TOKENS, elapsedTime * (MAX_TOKENS / tokenDurationPeriod));
			while (queue.size() < availTokens) {
				queue.add(true);
			}
		}

	}

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		TokenBucketFilter t = new TokenBucketFilter(5, 2);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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

}
