package designs.multithreading.Synchronization;

public class ThreadGroupEx {

	static class Worker implements Runnable {
		@Override
		public void run() {
			Long threadId = Thread.currentThread().getId();
			String groupName = Thread.currentThread().getThreadGroup().getName();
			System.out.println(threadId + " : " + groupName);

		}
	}

	public static void main(String[] args) {
		ThreadGroup parent = new ThreadGroup("parent");
		ThreadGroup child1 = new ThreadGroup(parent, "child1");
		ThreadGroup child2 = new ThreadGroup(parent, "child2");
		Thread t1 = new Thread(parent, new Worker());
		t1.start();
		Thread t2 = new Thread(child1, new Worker());
		t2.start();
		Thread t3 = new Thread(child2, new Worker());
		t3.start();

		parent.getMaxPriority();
		parent.activeCount();
		parent.activeGroupCount();
		parent.enumerate(new Thread[20]);

	}

}
