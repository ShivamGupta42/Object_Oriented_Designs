package designs.multithreading.Atomicity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicVariables {

	static class Test {

	}

	public static void main(String[] args) {
		AtomicIntegerArray atm = new AtomicIntegerArray(20);
		AtomicBoolean ab = new AtomicBoolean();
		AtomicLong al = new AtomicLong();
		
		atm.addAndGet(0, 1);
		atm.compareAndExchange(0, 0, -1);
		atm.decrementAndGet(10);
        ab.set(false);
		
		
		AtomicReference<Test> ref = new AtomicReference<Test>();
	}
}
