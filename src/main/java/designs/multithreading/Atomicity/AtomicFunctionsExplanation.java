package designs.multithreading.Atomicity;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicFunctionsExplanation {

	private AtomicInteger aInt = new AtomicInteger(0);

	public void increment1() {
		aInt.getAndIncrement();
	}

	public void increment2() {
		boolean updated = false;

		while (!updated) {
			int prevValue = aInt.get();
			updated = aInt.compareAndSet(prevValue, prevValue + 1);
		}

	}

	public int getCounter() {
		return aInt.get();
	}

	
	
}
