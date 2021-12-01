package designs.parkingLotDesign;

public class ParkingLotControlSystem {

	private BillGenerator billGen;
	private LevelManager manager;

	private ParkingLotControlSystem() {
		billGen = new BillGenerator(10);
		manager = new LevelManager();
	}

	static class ParkingLotControSystemInstanceProvider {
		static ParkingLotControlSystem instance = new ParkingLotControlSystem();
	}

	static ParkingLotControlSystem getInstance() {
		return ParkingLotControSystemInstanceProvider.instance;
	}

	void enterVehicle(String licenseNumber, VehicleType type) {
		Vehicle v = new Vehicle(licenseNumber, type);
		if (!manager.enterVehicle(v)) {
			System.out.println("There is no space!");
		}
	}

	void generateBill(String licenseNumber) {
		Slot slot = manager.searchVehicle(licenseNumber);
		if (slot == null || slot.currentVehicle == null) {
			System.out.println("Vehicle is not registered");
			return;
		}
		billGen.generateBill(slot.currentVehicle);
		slot.releaseSlot();
	}

}
