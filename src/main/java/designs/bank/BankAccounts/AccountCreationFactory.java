package designs.bank.BankAccounts;

import java.util.logging.Logger;

public class AccountCreationFactory {

	private static Logger logger = Logger.getLogger(AccountCreationFactory.class.getName());

	// Thread-safe (Stateless)
	public static BankAccount getInstance(String type, Double amount) throws BankAccountException {
		BankAccount newAccount = null;

		switch (type) {
		case "Current":
			newAccount = new CurrentAcount(amount, 20000.0, 0.0);
			break;
		case "Saving":
			newAccount = new SavingsAccount(amount, 10000.0, 4.50);
			break;
		default:
			throw new BankAccountException(logger, type + " account type is not supported!");
		}

		return newAccount;
	}

}
