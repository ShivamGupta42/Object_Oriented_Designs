package designs.bank.Branches;

import java.util.logging.Logger;

//Thread-safe
public class BranchException extends Exception {

	public BranchException(Logger logger, String errorMsg) {
		synchronized (logger) {
			logger.warning(errorMsg);
		}
	}

}
