package designs.vendingMachine.Exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentNotVerified extends Exception {

	public PaymentNotVerified(String message, Logger logger) {
		logger.log(Level.INFO, message);
	}
}
