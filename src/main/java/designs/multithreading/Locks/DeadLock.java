package designs.multithreading.Locks;

public class DeadLock {
	
	public static void main(String[] args) {
		
		DeadLock dead = new DeadLock();
		final A a = new A();
		final B b = new B();
		
		
		
		Runnable start1 = new Runnable() {
			
			@Override
			public void run() {
				synchronized (a) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					synchronized (b) {}
				}
				
			}
		};
		
		
		Runnable start2 = new Runnable() {
			
			@Override
			public void run() {
				synchronized (b) {
					
					synchronized (a) {
						
					}
					
				}
				
				
				
			}
		};
		
		new Thread(start1).start();
		new Thread(start2).start();
		
	}
	
	
	
	static class A{
		
	}
	
	static class B{
		
		
	}
}
