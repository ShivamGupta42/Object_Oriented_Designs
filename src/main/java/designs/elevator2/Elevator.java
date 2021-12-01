package designs.elevator2;

import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Elevator implements Runnable {

	enum Direction {
		UP, DOWN;
	}

	// three directions, none means stop
	private Direction direction = null;

	enum Status {
		WAITING, OPEN, CLOSE, RUNNING;
	}

	private Status status = Status.WAITING;
	private static final int MIN_FLOOR = 1;
	private static final int NO_SUCH_FLOOR = -1;
	private int currentFloor = 1;
	
	// next request floor may be no floor when stop
	private int nextFloor = NO_SUCH_FLOOR;
	private final BitSet requests;

	/**
	 * Create with floor count. Floor starts from one. e.g 10 floors means 1..10.
	 * bitset b = nw Bitset(10);
	 * 0 0 0 0 0 0 0 0 0 0 
	 * b.set(3);
	 * 0 0 1 0 0 0 0 0 0 0
	 * @param floors
	 */
	public Elevator(int floors) {
		this.requests = new BitSet(floors);
	}

	/**
	 * Receive floor request. Set next floor directly when stop, or try to insert
	 * next floor if current floor + 1 < request floor < original next floor.
	 * 
	 * @param floorNumber floor number
	 */
	public void receiveRequest(int floorNumber) {
		requests.set(floorNumber);
		System.out.println(String.format("get floor request [%d]", floorNumber));
		if (nextFloor == NO_SUCH_FLOOR) {
			setNextFloor(floorNumber);
		} else if ((currentFloor + 1 <= floorNumber) && floorNumber < nextFloor) {
			nextFloor = floorNumber;
		}
	}

	private void setNextFloor(int floorNumber) {
		if (floorNumber == NO_SUCH_FLOOR) {
			direction = null;
			nextFloor = NO_SUCH_FLOOR;
			status = Status.WAITING;
		} else {
			direction = currentFloor < floorNumber ? Direction.UP : Direction.DOWN;
			nextFloor = floorNumber;
			status = Status.RUNNING;
		}
	}

	private void openAndCloseDoor() {
		status = Status.OPEN;
		System.out.println("open door");
		status = Status.CLOSE;
		System.out.println("close door");
	}

	/**
	 * @see Runnable#run()
	 */
	public void run() {
		// return if not running, may be opening and closing door
		if (status != Status.RUNNING)
			return;
		// test if reach requested floor
		// if reached, open and close door, find next request floor
		testIfReachRequestFloor();
		// if no next floor found, don't move
		if (nextFloor == NO_SUCH_FLOOR)
			return;
		// or simulate moving
		currentFloor += (direction == Direction.UP ? 1 : -1);
		// print status itself
		System.out.println(this);
	}

	private void testIfReachRequestFloor() {
		if (currentFloor != nextFloor)
			return;
		System.out.println("reach floor " + currentFloor);
		openAndCloseDoor();
		requests.clear(currentFloor);
		setNextFloor(findNextRequestFloor());
	}

	private int findNextRequestFloor() {

		// when move upward, find upward then downward
		if (direction == Direction.UP) {
			int nextFloorUpward = findRequestFloorUpward(currentFloor + 1);
			if (nextFloorUpward != NO_SUCH_FLOOR)
				return nextFloorUpward;
			return findRequestFloorDownward(currentFloor - 1);
		}
		// must be down
		// find downward then upward
		int nextFloorDownward = findRequestFloorDownward(currentFloor - 1);
		if (nextFloorDownward != NO_SUCH_FLOOR)
			return nextFloorDownward;
		return findRequestFloorUpward(currentFloor + 1);
	}

	private int findRequestFloorUpward(int start) {
		for (int i = start; i < requests.size(); i++) {
			if (requests.get(i))
				return i;
		}
		return NO_SUCH_FLOOR;
	}

	private int findRequestFloorDownward(int start) {
		for (int i = start; i >= MIN_FLOOR; i--) {
			if (requests.get(i))
				return i;
		}
		return NO_SUCH_FLOOR;
	}

	@Override
	public String toString() {
		return String.format("Elevator [direction=%s, status=%s, currentFloor=%s, nextFloor=%s, requests=%s]",
				direction, status, currentFloor, nextFloor, requests);
	}

	public static void main(String[] args) throws InterruptedException {
		int floors = 10;
		Elevator elevator = new Elevator(floors); // 1..10
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleAtFixedRate(elevator, 0, 1, TimeUnit.SECONDS);
		Random random = new Random();
		for (int i = 0; i < floors; i++) {
			elevator.receiveRequest(random.nextInt(floors - 1) + 1);
			Thread.sleep(1000L);
		}
		
		executorService.shutdown();
	}

}