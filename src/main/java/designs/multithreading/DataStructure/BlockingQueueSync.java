package designs.multithreading.DataStructure;

import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueSync<T> {

	private T[] queue;
	private int limit = 10;
	int head = 0, tail = 0;
	AtomicInteger size = new AtomicInteger(0);

	public BlockingQueueSync(int limit) {
		this.limit = limit;
		this.queue = (T[]) new Object[limit];
	}

	public  void enqueue(T item) throws InterruptedException {

		synchronized (queue) {

			// Blocking Queue Function
			while (size.get() == limit) {
				wait();
			}
			// Queue Function
			queue[++tail] = item;
			size.incrementAndGet();

			// Optimized Blocking Queue Function
			// If this statement was not here, then only those threads
			// would be notified which are in this synchronization queue
			// instead of the threads which are also waiting for the lock on queue
			// but which are in the Dq synchronization Queue
			if (size.get() == 1) {
				notifyAll();
			}
		}
	}

	public Object dequeue() throws InterruptedException {

		synchronized (queue) {
			// Blocking Queue Function
			while (size.get() == 0) {
				wait();
			}

			// Queue Function
			T item = queue[head];
			head++;
			size.decrementAndGet();

			//// To notify all the producer threads
			if (size.get() == limit - 1) {
				notifyAll();
			}
			return item;
		}
	}

	public Boolean isEmpty() {
		return size.get() == 0 ? true : false;
	}

}