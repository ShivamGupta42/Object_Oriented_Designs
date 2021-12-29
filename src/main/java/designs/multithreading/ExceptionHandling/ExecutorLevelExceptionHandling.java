package designs.multithreading.ExceptionHandling;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorLevelExceptionHandling {

	static class CustThreadPoolExec extends ThreadPoolExecutor {

		public CustThreadPoolExec(int idlePoolSize, int maxPoolSize, int keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> q) {
			super(idlePoolSize, maxPoolSize, keepAliveTime, unit, q);
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			if (t != null) {
				System.out.println(t.getCause());
			}
		}
	}

	
}
