package designs.bank.BankAccounts;

//Thread-safe
public class SavingsAccount extends BankAccount {

	public SavingsAccount(double amount, double minimumBalance, double interestRate) {
		super(amount, minimumBalance, interestRate);
	}

	@Override
	String getType() {
		return "Saving";
	}
}
