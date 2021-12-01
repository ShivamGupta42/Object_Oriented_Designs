package designs.bank.Customer;

import java.util.logging.Logger;

//Thread-safe
public class CustomerException extends Exception {

	public CustomerException(Logger logger, String errorMsg) {
		synchronized (logger) {
			logger.warning(errorMsg);
		}

	}

}
