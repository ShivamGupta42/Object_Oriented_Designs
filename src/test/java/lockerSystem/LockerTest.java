package lockerSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import designs.lockerSystem.DelieveryAgent;
import designs.lockerSystem.LMSException;
import designs.lockerSystem.Locker;
import designs.lockerSystem.LockerManagementSystem;
import designs.lockerSystem.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LockerTest {

	static LockerManagementSystem lms;

	@BeforeEach
	void setUpLMS() {

		List<Locker> totalLockers = new ArrayList<Locker>();
		totalLockers.add(new Locker(20, 1, 560034));
		totalLockers.add(new Locker(10, 2, 560030));
		totalLockers.add(new Locker(30, 3, 560031));
		lms = new LockerManagementSystem(totalLockers);
	}

	@Test
	void VerifiedAgentGettingALock() {
		DelieveryAgent agent1 = new DelieveryAgent(1, "Ramesh");
		Package pack = new Package(1, 15, "");
		Locker locker = null;
		try {
			locker = lms.getLocker(agent1.getId(), pack);
		} catch (LMSException e) {
			e.printStackTrace();
		}

		assertNotNull(locker);
	}

	@Test
	void UnverifiedAgentTest() {
		Assertions.assertThrows(LMSException.class, () -> UnVerfiedAgentNotGettingALock());
	}

	private void UnVerfiedAgentNotGettingALock() throws LMSException {
		DelieveryAgent agent1 = new DelieveryAgent(100, "Ramesh");
		Package pack = new Package(1, 15, "");
		Locker locker = null;
		locker = lms.getLocker(agent1.getId(), pack);
	}

	@Test
	void useUpAllLockersTest() {
		Assertions.assertThrows(LMSException.class, () -> useUpAllLockers());
	}

	void useUpAllLockers() throws LMSException {
		Package pack = new Package(1, 15, "");
		DelieveryAgent agent1 = new DelieveryAgent(1, "Ramesh");
		lms.getLocker(agent1.getId(), pack);
		lms.getLocker(agent1.getId(), pack);
		lms.getLocker(agent1.getId(), pack);

	}

	@Test
	// Should provide a locker which is just above or equal to the package size
	void verifyLockerSize() throws LMSException {
		Package pack = new Package(1, 15, "");
		DelieveryAgent agent1 = new DelieveryAgent(1, "Ramesh");
		Locker locker = lms.getLocker(agent1.getId(), pack);
		assertEquals(20, locker.getSize());
	}

}
