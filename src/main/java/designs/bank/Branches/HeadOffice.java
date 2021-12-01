package designs.bank.Branches;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

//Thread-safe
public class HeadOffice {

	private Logger logger = Logger.getLogger(HeadOffice.class.getName());

	private List<Branch> branches;

	AtomicLong lastCreatedBranch;

	public HeadOffice() {
		branches = new ArrayList<Branch>();
		lastCreatedBranch = new AtomicLong(0L);
	}

	public synchronized Branch createBranch() {
		Branch newBranch = new Branch();
		long newBranchId = lastCreatedBranch.incrementAndGet();
		newBranch.setBranchId(newBranchId + "");
		synchronized (branches) {
			branches.add(newBranch);
		}

		return newBranch;
	}

	public Branch getBranchById(String branchId) throws BranchException {

		synchronized (branches) {
			Optional<Branch> foundBranch = branches.parallelStream()
					.filter(e -> e.getBranchId().equalsIgnoreCase(branchId)).findAny();

			// If Branch was not found throw exception
			String errorMsg = "Branch with provided branchId: " + branchId + " was not found in records";
			foundBranch.orElseThrow(() -> new BranchException(logger, errorMsg));

			// Otherwise return value
			return foundBranch.get();
		}

	}

	public List<Branch> getAllBranches() {
		return new ArrayList<Branch>(branches);
	}
}
