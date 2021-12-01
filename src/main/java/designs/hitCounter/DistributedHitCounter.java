package designs.hitCounter;

import java.util.HashSet;

public class DistributedHitCounter {

	HashSet<HitCounterSingleMachine> listOfMachines;

	void registerMachine(HitCounterSingleMachine machine) {
		listOfMachines.add(machine);
	}

	void deRegisterMachine(HitCounterSingleMachine machine) {
		listOfMachines.remove(machine);
	}

	long getHitsOfAllMachines(int timeStamp) {
		long result = 0L;

		for (HitCounterSingleMachine machine : listOfMachines) {
			result += machine.getHits(timeStamp);
		}

		return result;
	}

}
