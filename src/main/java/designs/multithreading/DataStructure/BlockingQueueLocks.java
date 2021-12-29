package designs.multithreading.DataStructure;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueLocks<T> {
	private T[] backingArray;
	private int capacity;
	int size = 0;
	int head = 0, tail = 0;
	private Lock lock = new ReentrantLock();
	private Condition enQCondition = lock.newCondition();
	private Condition dQCondition = lock.newCondition();

	public BlockingQueueLocks(int capacity) {
		this.capacity = capacity;
		backingArray = (T[]) new Object[capacity];
	}

	public void enQ(T data) throws InterruptedException {
		lock.lock();

		while (size == capacity) {
			enQCondition.await(3, TimeUnit.SECONDS);
		}

		if (tail == capacity) {
			tail = 0;
		}

		backingArray[tail++] = data;
		dQCondition.signalAll();

		size++;
		dbConnection(lock);

	}

	private void dbConnection(Lock lock) {
		lock.unlock();
	}

	public T dQ() throws InterruptedException {
		lock.lock();

		while (size == 0) {
			dQCondition.await();
		}

		if (head == capacity) {
			head = 0;
		}

		T data = backingArray[head++];

		size--;
		enQCondition.signalAll();
		lock.unlock();

		return data;

	}

}
