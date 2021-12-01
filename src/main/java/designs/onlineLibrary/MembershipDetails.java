package designs.onlineLibrary;

import java.util.HashMap;

public class MembershipDetails {
	// MemberId and membership duration left
	static HashMap<Integer, Integer> validMemberShips;

	public MembershipDetails() {
		validMemberShips = new HashMap<Integer, Integer>();
	}

	boolean checkValidMember(int memberId) {
		Integer duration = validMemberShips.get(memberId);

		if (duration == null || duration <= 0) {
			return false;
		}
		return true;
	}

	void extension(int memberId, int days) {
		Integer duration = validMemberShips.get(memberId);
		if (duration == null) {
			// throw memberNotPresentException
		}

		validMemberShips.put(memberId, duration + days);
	}

}
