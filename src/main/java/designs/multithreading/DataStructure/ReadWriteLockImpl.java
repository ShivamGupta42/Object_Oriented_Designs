package designs.multithreading.DataStructure;


public class ReadWriteLockImpl {
	
	boolean isWriteLocked = false;
	int readers = 0;

	synchronized void acquireReadLock() throws InterruptedException {
		if (isWriteLocked) {
			wait();
		}

		readers++;
	}

	synchronized void releaseReadLock() {
		readers--;
		notify();
	}

	synchronized void acquireWriteLock() throws InterruptedException {
		if (readers != 0 || isWriteLocked) {
			wait();
		}

		isWriteLocked = true;
	}

	synchronized void releaseWriteLock() {
		isWriteLocked = false;
		notify();
	}
}



