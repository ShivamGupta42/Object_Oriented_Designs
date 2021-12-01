package designs.parkingLotDesign;

import java.util.List;
import java.util.Optional;

public class Level {
	List<Slot> allSlots;

	void addSlot(Slot slot) {
		allSlots.add(slot);
	}

	boolean putVehicleInASlot(Vehicle v) {
		int size = v.getType().minSpaceRequiredSquareFeet;
		Optional<Slot> slot = allSlots.parallelStream().filter(e -> e.getType().size >= size && e.isAvailable)
				.findFirst();

		slot.ifPresent(e -> e.setCurrentVehicle(v));
		return slot.isPresent();
	}

	Optional<Slot> searchForVehicle(String licenseNumber) {

		Optional<Slot> slot = allSlots.parallelStream()
				.filter(e -> e.getCurrentVehicle().licenseNumber.equals(licenseNumber)).findFirst();

		return slot;
	}

}
