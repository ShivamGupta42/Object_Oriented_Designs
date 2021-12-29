package designs.multithreading.ThreadPools;

import designs.multithreading.DataStructure.BlockingQueueSync;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

	// Task queue
	private BlockingQueueSync<Runnable> taskQueue = null;

	// Thread List
	private List<PoolThread> threads = new ArrayList<PoolThread>();

	// Current ThreadPool state
	private boolean isStopped = false;

	public ThreadPool(int noOfThreads, int maxNoOfTasks) {
		taskQueue = new BlockingQueueSync(maxNoOfTasks);

		// creating core threads
		for (int i = 0; i < noOfThreads; i++) {
			threads.add(new PoolThread(taskQueue));
		}

		// starting core threads
		for (PoolThread thread : threads) {
			thread.start();
		}
	}

	// Submits tasks to the task queue
	public synchronized void execute(Runnable task) throws Exception {
		if (this.isStopped)
			throw new IllegalStateException("ThreadPool is stopped");

		this.taskQueue.enqueue(task);
	}

	// Calls the doStop method of Thread List
	public synchronized void shutdown() {
		this.isStopped = true;
		for (PoolThread thread : threads) {
			thread.doStop();
		}
	}

}