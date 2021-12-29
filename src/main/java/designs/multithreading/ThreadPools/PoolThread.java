package designs.multithreading.ThreadPools;

import designs.multithreading.DataStructure.BlockingQueueSync;



public class PoolThread extends Thread {

	// Instance of shared threadPool queue
	private BlockingQueueSync<Runnable> taskQueue = null;

	// Current Thread Status
	private boolean isThisThreadStopped = false;

	// Current Task time
	Long start = System.currentTimeMillis();

	public PoolThread(BlockingQueueSync queue) {
		taskQueue = queue;
	}

	public void run() {
		while (!isThisThreadStopped()) {
			try {
				Runnable runnable = (Runnable) taskQueue.dequeue();
				runnable.run();
				System.out.println();
			} catch (Exception e) {
				// log or otherwise report exception,
				// but keep pool thread alive.
			}
		}
	}

	public synchronized void doStop() {
		isThisThreadStopped = true;
		this.interrupt(); // break pool thread out of dequeue() call.
	}

	public synchronized boolean isThisThreadStopped() {
		return isThisThreadStopped;
	}

}