package designs.multithreading.DataStructure;

import java.util.concurrent.Semaphore;

public class BlockingQueueSemaphore<T> {

	private T[] backingArray;
	private int capacity;
	private int size = 0;
	private int head = 0, tail = 0;

	private Semaphore prodSemaphore;
	private Semaphore conSemaphore;

	public BlockingQueueSemaphore(int capacity, int permits) {
		prodSemaphore = new Semaphore(permits);
		conSemaphore = new Semaphore(permits);
	}

	public void enQ(T data) {

		try {
			do {
				prodSemaphore.release();
				prodSemaphore.acquire();
			} while (tail > capacity - 1);

			backingArray[tail++] = data;

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			conSemaphore.release();
		}

	}

	public T dQ() {
		T data = null;

		try {
			do {
				conSemaphore.release();
				conSemaphore.acquire();
			} while (tail < 0);

			data = backingArray[tail--];

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			prodSemaphore.release();
		}

		return data;
	}
}
