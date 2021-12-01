package designs.trafficControlSystem;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TrafficControlSystem implements Runnable {

	private Logger logger = Logger.getLogger(TrafficControlSystem.class.getName());

	TrafficLight currentState;
	boolean isControlSystemOnline = true;

	public TrafficControlSystem(TrafficLight initStatus) {
		currentState = initStatus;
	}

	private void switchToState(TrafficLight state) {
		currentState = state;
		System.out.println("Traffic state changed to :" + currentState);

	}

	private void switchState() {

		switch (currentState) {
		case RED:
			switchToState(TrafficLight.YELLOW);
			break;

		case YELLOW:
			switchToState(TrafficLight.GREEN);
			break;

		case GREEN:
			switchToState(TrafficLight.RED);
			break;

		}
	}

	synchronized void shutControlSystem() {
		isControlSystemOnline = false;
	}

	synchronized void changeStateButtonPress() {
		switchState();
	}

	@Override
	public void run() {
		while (isControlSystemOnline) {
			try {
				Thread.sleep(currentState.duration * 1000);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, "Control System was interruptted");
			}

			switchState();
		}

	}

}
