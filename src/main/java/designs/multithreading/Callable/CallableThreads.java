package designs.multithreading.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableThreads {

	static class StringCallable implements Callable<String> {
		private int id;

		public StringCallable(int id) {
			this.id = id;
		}

		@Override
		public String call() throws Exception {
			return id + " value returned";
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// Single callable execution using a future task
		FutureTask<String> task = new FutureTask<String>(new StringCallable(1));
		Thread t = new Thread(task);
		t.start();
		System.out.println(task.get());

		// Multiple callable execution using Executor service
		ExecutorService es = Executors.newFixedThreadPool(1);
		Future<String> f1 = es.submit(new StringCallable(2));
		Future<String> f2 = es.submit(new StringCallable(3));

		// Calling ExecutorService shutdown
		es.shutdown();

		System.out.println(f1.get());
		System.out.println(f2.get());

	}

	
	
}
