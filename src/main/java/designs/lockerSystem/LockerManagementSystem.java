package designs.lockerSystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class LockerManagementSystem {

	private TreeSet<Locker> availableLockers;
	private HashMap<Locker, Integer> lockersInUse;
	private HashSet<Integer> verifiedIds;

	public LockerManagementSystem(List<Locker> totalLockers) {

		this.availableLockers = new TreeSet<Locker>(totalLockers);
		lockersInUse = new HashMap<Locker, Integer>();
		initializeVerifiedIds();
		// initializeLockers();
	}

	private void initializeLockers() {
		availableLockers.add(new Locker(10, 1, 1));
		availableLockers.add(new Locker(20, 2, 1));
		availableLockers.add(new Locker(30, 3, 1));

	}

	private void initializeVerifiedIds() {
		verifiedIds = new HashSet<>();
		verifiedIds.add(1);
		verifiedIds.add(2);
		verifiedIds.add(3);
	}

	public synchronized Locker getLocker(int agentId, Package p) throws LMSException {
		if (!verifiedIds.contains(agentId)) {
			throw new LMSException("Agent Not verified!");
		}

		if (availableLockers.size() == 0) {
			throw new LMSException("No available lockers!");
		}

		int package_size = p.getSize();
		Locker availableLocker = availableLockers.ceiling(new Locker(package_size, 1, 1));

		if (availableLocker != null) {
			availableLocker.storePackage(p);
			lockersInUse.put(availableLocker, agentId);
			availableLockers.remove(availableLocker);
		} else {
			throw new LMSException("Package cannot be stored in any current locker!");
		}

		return availableLocker;
	}

	synchronized void releaseLocker(Locker locker, int agentId) {
		if (verifiedIds.contains(agentId) && lockersInUse.containsKey(locker)) {
			lockersInUse.remove(locker);
			availableLockers.add(locker);
		}

	}

}
