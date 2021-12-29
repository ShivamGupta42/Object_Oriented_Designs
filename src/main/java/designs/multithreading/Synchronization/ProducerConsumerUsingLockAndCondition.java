package designs.multithreading.Synchronization;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerUsingLockAndCondition {
	// Shared resources used by all threads in the system
	static class SharedResource {
		private static Queue<Integer> queue;
		private static int MAX_QUEUE_SIZE;
		private static Random random;

		public SharedResource() {
			queue = new LinkedList<Integer>();
			random = new Random();
			MAX_QUEUE_SIZE = 10;
		}
	}

	static class ProducerConsumerImplementation {
		// create lock instance followed by condition variable
		private final Lock lock = new ReentrantLock();
		private final Condition bufferFull = lock.newCondition();
		private final Condition bufferEmpty = lock.newCondition();

		public void put() {
			try {
				
				lock.lock(); // Acquire lock and block other threads
				while (SharedResource.queue.size() == SharedResource.MAX_QUEUE_SIZE) {
					System.out.println("Size of buffer is " + SharedResource.queue.size());
					bufferFull.await(); // wait till buffer is full, no place to
					// store
				} // while close
				int nextval = (Integer) SharedResource.random.nextInt();
				boolean status = (Boolean) SharedResource.queue.offer(nextval);
				if (status) {
					System.out.println(
							"Thread " + Thread.currentThread().getName() + " added value " + nextval + "in queue ");
					bufferEmpty.signalAll();// similar to notifyAll -
					// communicate waiting thread
					// that queue is not empty now

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();// Release lock
			}
		}

		public void get() {
			Integer returnVal = Integer.MIN_VALUE;
			try {
				lock.lock();// aquire lock
				while (SharedResource.queue.size() == 0) {
					System.out.println("No element in Buffer, wait at least one element is available");
					bufferEmpty.await();
				}
				System.out.println("Size of buffer is " + SharedResource.queue.size());
				returnVal = (Integer) SharedResource.queue.poll();
				if (returnVal != null) {
					System.out.println("Thread " + Thread.currentThread().getName() + " consumed value " + returnVal
							+ " in queue ");

					bufferFull.signalAll();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();// release lock
			}
		}
	}

	static class Producer implements Runnable {
		SharedResource sharedObj;
		ProducerConsumerImplementation pci;

		public Producer(SharedResource sharedObj, ProducerConsumerImplementation pci) {
			this.sharedObj = sharedObj;
			this.pci = pci;
		}

		public void run() {
			int i = 0;
			while (true) {
				pci.put();
				i++;
			}
		}
	}

	static class Consumer implements Runnable {
		SharedResource sharedObj;
		ProducerConsumerImplementation pci;

		public Consumer(SharedResource sharedObj, ProducerConsumerImplementation pci) {
			this.sharedObj = sharedObj;
			this.pci = pci;
		}

		public void run() {
			int i = 0;
			while (true) {
				pci.get();
				i++;
			}
		}
	}

	public static void main(String[] args) {
		SharedResource sharedObj = new SharedResource();
		ProducerConsumerImplementation pci = new ProducerConsumerImplementation();
		Thread tp1 = new Thread(new Producer(sharedObj, pci), "producer-1");
		Thread tc1 = new Thread(new Consumer(sharedObj, pci), "consumer-1");
		// Thread tp2 = new Thread(new Producer(sharedObj, pci), "producer-2");
		// Thread tc2 = new Thread(new Consumer(sharedObj, pci), "consumer-2");
		tc1.start();
		tp1.start();
		// tc2.start();
		// tp2.start();
	}
}