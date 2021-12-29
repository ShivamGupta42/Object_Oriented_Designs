package designs.multithreading.ExceptionHandling;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.stream.Stream;

public class ThreadLevelExceptionHandler {

	static class ThreadExceptionHandler implements UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());

			System.out.println(t.getId());
			System.out.println(t.getThreadGroup());
			//new Thread(new Worker()).start();
		}
	}

	static class Worker implements Runnable {
		@Override
		public void run() {
			//Thread.currentThread().setUncaughtExceptionHandler(new ThreadExceptionHandler());
			System.out.println("123");
			System.out.println(Integer.parseInt("abc"));

		}
	}

	
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Worker());
		t1.start();
		Stream.iterate(0, i->i+1).limit(1000).forEach(System.out::print);
	}

}
