package designs.multithreading.DataStructure;

import java.util.ArrayList;

class Executor {

	public void asynchronousExecution(Callback callback) throws Exception {

		Thread t = new Thread(() -> {
			try {
				// Simulate useful work by sleeping for 5 seconds
				Thread.sleep(5000);
			} catch (InterruptedException ie) {
			}
			callback.done();
		});
		t.start();
		t.join();
	}
}

class ExecutorSecond extends Executor {

	@Override
	public void asynchronousExecution(Callback callback) throws Exception {
		Object signal = new Object();
		boolean[] isDone = new boolean[1];

		Callback cb = new Callback() {
			@Override
			public void done() {
				callback.done();

				synchronized (signal) {
					signal.notifyAll();
					isDone[0] = true;
				}

			}
		};
		super.asynchronousExecution(cb);

		synchronized (signal) {
			while (!isDone[0]) {
				signal.wait();
			}
		}

	}

}

interface Callback {
	void done();
}

public class FromAsyncToSync_OldCode {

	public static void main(String[] args) throws Exception {

		Executor exec = new Executor();
		exec.asynchronousExecution(() -> System.out.println("Third thread"));

		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(3000);
				System.out.println("Second thread");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		t2.start();
		t2.join();
		System.out.println("Main thread exiting");
		
		ArrayList<Integer> arrayList = new  ArrayList<Integer>();
		//[1...10]- remove even integers
		arrayList.removeIf(e->e%2==0);
		
		//[1...10]- increment each number
		arrayList.replaceAll(e->e+1);
		
		
	}

}
