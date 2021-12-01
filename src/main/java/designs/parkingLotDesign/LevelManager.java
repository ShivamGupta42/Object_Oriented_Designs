package designs.parkingLotDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LevelManager {

	private List<Level> allLevels;

	public LevelManager() {
		allLevels = new ArrayList<Level>();
		initializeAllLevels();
	}

	private void initializeAllLevels() {
		Level one = new Level();
		one.addSlot(new Slot(SlotType.SMALL));
		one.addSlot(new Slot(SlotType.SMALL));
		one.addSlot(new Slot(SlotType.SMALL));

		Level two = new Level();
		two.addSlot(new Slot(SlotType.SMALL));
		two.addSlot(new Slot(SlotType.MEDIUM));
		two.addSlot(new Slot(SlotType.MEDIUM));

		Level three = new Level();
		three.addSlot(new Slot(SlotType.SMALL));
		three.addSlot(new Slot(SlotType.BIG));
		three.addSlot(new Slot(SlotType.BIG));

		allLevels.add(one);
		allLevels.add(two);
		allLevels.add(three);
	}

	public boolean enterVehicle(Vehicle v) {
		for (Level l : allLevels) {
			if (l.putVehicleInASlot(v)) {
				return true;
			}
		}

		return false;
	}

	public Slot searchVehicle(String licenseNumber) {
		for (Level l : allLevels) {
			Optional<Slot> slot = l.searchForVehicle(licenseNumber);
			if (slot.isPresent()) {
				return slot.get();
			}
		}

		return null;
	}

}
