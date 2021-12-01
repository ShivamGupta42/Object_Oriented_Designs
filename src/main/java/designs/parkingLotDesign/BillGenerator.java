package designs.parkingLotDesign;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Vector;

public class BillGenerator {

	int maxAllottedtime;

	public BillGenerator(int maxAlottedTime) {
		this.maxAllottedtime = maxAlottedTime;
	}

	public void generateBill(Vehicle v) {
		Vehicle result = null;
		LocalDateTime entryTime = result.entryTime;
		System.out.println("Total bill :" + (LocalDateTime.now().getHour() - entryTime.getHour()) * 10);
	}

}
