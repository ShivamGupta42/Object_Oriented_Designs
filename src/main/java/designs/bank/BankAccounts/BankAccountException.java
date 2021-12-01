package designs.bank.BankAccounts;

import java.util.logging.Logger;

//Thread-safe
public class BankAccountException extends Exception {

	public BankAccountException(Logger logger, String string) {
		synchronized(logger) {
		logger.warning(string);
		}
	}

}
