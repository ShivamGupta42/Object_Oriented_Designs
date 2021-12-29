package designs.multithreading.Synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksThreeThreadsSync {
	static Lock lock = new ReentrantLock(true);
	static Condition one = lock.newCondition();
	static Condition two = lock.newCondition();
	static Condition three = lock.newCondition();

	public static void main(String[] args) {
		LocksThreeThreadsSync one = new LocksThreeThreadsSync();
		try {
			LocksThreeThreadsSync two = (LocksThreeThreadsSync) one.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
