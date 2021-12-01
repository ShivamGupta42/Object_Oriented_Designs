package designs.elevator2;

import java.util.EnumMap;

public class Lift {

	// Floor and Waiting people mapping
	final int MAX_CAPACITY;
	int curCapacity;
	
	private EnumMap<Floors, Integer> goingUp;
	private EnumMap<Floors, Integer> goingDown;
	private Thread liftThread;
	volatile boolean isLiftStopped = true;

	public Lift(int capacity) {
		// Set maximum capacity of lift
		MAX_CAPACITY = capacity;

		// Set current capacity
		curCapacity = 0;

		// initializeMap
		initMap();

		// initializeLiftThread
		liftThread = new Thread(() -> {
			isLiftStopped = false;
			while (!isLiftStopped) {
				simulateLiftGoingUP();
				simulateLiftGoingDown();
			}
		});

	}

	private void initMap() {
		goingUp = new EnumMap<Floors, Integer>(Floors.class);
		goingDown = new EnumMap<>(Floors.class);
	}

	public synchronized void requestLift(Floors floor, String direction) throws IllegalAccessException {

		if (direction.equalsIgnoreCase("UP")) {

			goingUp.compute(floor, (key, value) -> {
				if (value != null) {
					return value + 1;
				} else {
					return 1;
				}
			});

		} else if (direction.equalsIgnoreCase("DOWN")) {
			goingDown.compute(floor, (key, value) -> {
				if (value != null) {
					return value + 1;
				} else {
					return 1;
				}
			});
		} else {
			throw new IllegalAccessException("Please choose a valid direction: UP/DOWN");
		}
	}

	// Once inside the lift
	// Method for getting outside
	public void getDown(Floors floor, String direction) {

	}

	// Simulates Lift Going Up/Down
	// Takes the people from each floor from the two maps until the maxCapacity of
	// the lift is reached
	public void simulateLiftGoingUP() {

		goingUp.replaceAll((Floor, People) -> {
			int remainingPeople = People;
			if (curCapacity < MAX_CAPACITY) {
				int UpdatedCapacity = Math.min(curCapacity + People, MAX_CAPACITY);
				remainingPeople = UpdatedCapacity - curCapacity;
				curCapacity = UpdatedCapacity;
			}

			return remainingPeople;
		});

	}

	public void simulateLiftGoingDown() {
		goingDown.replaceAll((Floor, People) -> {
			int remainingPeople = People;
			if (curCapacity < MAX_CAPACITY) {
				int UpdatedCapacity = Math.min(curCapacity + People, MAX_CAPACITY);
				remainingPeople = UpdatedCapacity - curCapacity;
				curCapacity = UpdatedCapacity;
			}

			return remainingPeople;
		});

	}

	public void simulateLift() {
		liftThread.start();
	}

	public void stopLift() {
		isLiftStopped = true;
	}

}
