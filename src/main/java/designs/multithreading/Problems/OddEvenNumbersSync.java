package designs.multithreading.Problems;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenNumbersSync {

	Lock syncLock = new ReentrantLock();
	Condition printEven = syncLock.newCondition();
	Condition printOdd = syncLock.newCondition();
	volatile boolean canPrintEven = false;

	
	public static void main(String[] args) {
		OddEvenNumbersSync sync = new OddEvenNumbersSync();
		Thread even = new Thread(sync.new printEven(10));
		Thread odd = new Thread(sync.new printOdd(10));
		even.start();
		odd.start();

	}

	class printEven implements Runnable {
		int printTill = -1;

		public printEven(int printTill) {
			this.printTill = printTill;
		}

		@Override
		public void run() {

			syncLock.lock();
			for (int i = 2; i <= printTill; i = i + 2) {
				while (!canPrintEven) {
					try {
						printEven.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				canPrintEven=false;
				System.out.println("PrintEven :"+i);
				printOdd.signal();
			}

			syncLock.unlock();

		}

	}

	class printOdd implements Runnable {
		int printTill = -1;

		public printOdd(int printTill) {
			this.printTill = printTill;
		}

		@Override
		public void run() {
			syncLock.lock();

			for (int i = 1; i <= printTill; i = i + 2) {
				while (canPrintEven) {
					try {
						printOdd.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("PrintOdd :"+i);
				canPrintEven=true;
				printEven.signal();
			}

			syncLock.unlock();

		}

	}

}
