package designs.multithreading.DataStructure;

public class CountingSemaphore {

	private int maxPermits;
	private int usedPermits;

	public CountingSemaphore(int maxPerits) {
		this.maxPermits = maxPerits;
		usedPermits = 0;
	}

	public synchronized void acquire() throws InterruptedException {
		while (usedPermits == maxPermits) {
			wait();
		}

		usedPermits++;
		notify();// So that one permit can be released
	}

	public synchronized void release() throws InterruptedException {
		while (usedPermits == 0) {
			wait();
		}

		usedPermits--;
		notify();// So that now a thread can acquire the permit
	}

}
