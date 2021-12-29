package designs.multithreading.Synchronization;

public class WaitNotifyTest {

	public static void main(String[] args) {
		System.out.println("Main Thread : "+Thread.currentThread().getName()+"");
		Thread t1 = new Thread(new waitTest());
		Thread t2 = new Thread(new waitTest());
		t1.start();
		//t2.start();
		

	}

	static class waitTest implements Runnable {
		public void run() {
			try {
				System.out.println("Other Threads : "+Thread.currentThread().getName()+"");
				Thread.currentThread().wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
	}
}
