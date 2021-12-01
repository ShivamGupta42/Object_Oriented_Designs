package designs.trafficControlSystem;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		TrafficControlSystem trafficControlSystem = new TrafficControlSystem(TrafficLight.RED);
		Thread t1 = new Thread(trafficControlSystem);
		t1.start();

		Thread t2 = new Thread(new RandomButtonPresser(trafficControlSystem));
		t2.start();

		// Problem with join statements
		// First join on t1 will be finished only then join on t2 will be active
		t1.join();
		t2.join();
		System.out.println("Waiting for t1 and t2 completion");
	}

	static class RandomButtonPresser implements Runnable {
		TrafficControlSystem trafficControlSystem;

		public RandomButtonPresser(TrafficControlSystem trafficControlSystem) {
			this.trafficControlSystem = trafficControlSystem;
		}

		@Override
		public void run() {

			for (int i = 0; i <= 5; i++) {
				sleep();
				System.out.println("\nTrying to change state by button press");
				trafficControlSystem.changeStateButtonPress();

			}

			trafficControlSystem.shutControlSystem();

		}

		void sleep() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
