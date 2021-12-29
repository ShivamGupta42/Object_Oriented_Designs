package designs.multithreading.Atomicity;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

	AtomicReference<Stack<Integer>> ref = new AtomicReference<>(new Stack<Integer>());

	public void push(int x) {
		boolean updated = false;

		while (!updated) {
			Stack<Integer> prevValue = ref.get();
			Stack<Integer> newValue = (Stack<Integer>) prevValue.clone();
			newValue.push(x);
			updated = ref.compareAndSet(prevValue, newValue);
		}
	}

	public int pop() {
		boolean updated = false;
		int x = 0;
		while (!updated) {
			Stack<Integer> prevValue = ref.get();
			Stack<Integer> newValue = (Stack<Integer>) prevValue.clone();
			x = newValue.pop();
			updated = ref.compareAndSet(prevValue, newValue);
		}

		return x;
	}

	public Stack<Integer> getVariable() {
		return ref.get();
	}

	
	
	public static void main(String[] args) throws InterruptedException {
		AtomicReferenceTest test = new AtomicReferenceTest();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					test.push(i);
				}

			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 100; i < 150; i++) {
					test.push(i);
				}

			}
		});

		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					test.pop();
				}

			}
		});

		t1.start();
		Thread.sleep(1000);
		t2.start();
		Thread.sleep(1000);
		t3.start();

		t1.join();
		t2.join();
		t3.join();

		System.out.println(test.getVariable().size());

	}

}
