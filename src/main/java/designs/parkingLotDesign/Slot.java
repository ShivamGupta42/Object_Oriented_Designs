package designs.parkingLotDesign;

public class Slot implements Comparable<Slot> {

	SlotType type;
	boolean isAvailable = true;
	Vehicle currentVehicle;

	public Slot(SlotType type) {

		this.type = type;
	}

	void parkVehicle(Vehicle v) {
		this.isAvailable = false;
		currentVehicle = v;
	}

	void releaseSlot() {
		this.isAvailable = true;
		this.currentVehicle = null;
	}

	public SlotType getType() {
		return type;
	}

	public void setType(SlotType type) {
		this.type = type;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Vehicle getCurrentVehicle() {
		return currentVehicle;
	}

	public void setCurrentVehicle(Vehicle currentVehicle) {
		this.currentVehicle = currentVehicle;
	}

	@Override
	public int compareTo(Slot o) {
		return this.getType().size - o.getType().size;
	}

}
