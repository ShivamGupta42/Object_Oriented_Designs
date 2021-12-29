package designs.multithreading.Synchronization;

public class YieldProducerConsumer {

	public static void main(String[] args) {
		Thread producer = new Thread(new Producer());
		Thread consumer = new Thread(new Consumer());
		producer.setPriority(Thread.MIN_PRIORITY);
		consumer.setPriority(Thread.MAX_PRIORITY);
		producer.start();
		consumer.start();

	}

	static class Producer implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 5; i++) {
				System.out.println("Producer thread : " + i);
				Thread.yield();
			}

		}

	}

	static class Consumer implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 5; i++) {
				System.out.println("Consumer thread : " + i);
				Thread.yield();
			}

		}

	}
}
