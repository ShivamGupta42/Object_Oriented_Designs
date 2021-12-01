package designs.bank.Customer;

import java.util.ArrayList;
import java.util.List;

import designs.bank.BankAccounts.BankAccount;

//Thread-safe
public class Customer {

	private String panNumber;
	private List<BankAccount> accounts;

	public Customer(String panNumber) {
		accounts = new ArrayList<BankAccount>();
		this.panNumber = panNumber;
	}

	public List<BankAccount> getBankAccounts() {
		return new ArrayList<>(accounts);
	}

	public void addBankAccount(BankAccount account) {
		synchronized (accounts) {
			accounts.add(account);
		}
	}

	public String getPanNumber() {
		return panNumber;
	}

	@Override
	public String toString() {
		return panNumber;
	}

}
