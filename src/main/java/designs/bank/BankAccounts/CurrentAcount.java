package designs.bank.BankAccounts;

//Thread-safe
public class CurrentAcount extends BankAccount {

	
	public CurrentAcount(double amount,double minimumBalance, double interestRate) {
		super(amount,minimumBalance,interestRate);
	}

	@Override
	String getType() {
		return "Current";
	}

}
