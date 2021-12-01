package designs.trafficControlSystem;

public enum TrafficLight {
	RED(40), YELLOW(20), GREEN(40);

	int duration;

	private TrafficLight(int duration) {
		this.duration = duration;
	}

}
