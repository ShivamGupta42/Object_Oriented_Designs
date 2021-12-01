package designs.parkingLotDesign;

public enum VehicleType {
	BIKES(5), CARS(10), TRUCKS(30);

	int minSpaceRequiredSquareFeet;

	private VehicleType(int minSpaceRequiredSquareFeet) {
		this.minSpaceRequiredSquareFeet = minSpaceRequiredSquareFeet;
	}

}
