package designs.bank.Branches;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import designs.bank.BankAccounts.AccountCreationFactory;
import designs.bank.BankAccounts.BankAccount;
import designs.bank.BankAccounts.BankAccountException;
import designs.bank.Customer.Customer;
import designs.bank.Customer.CustomerException;

//Thread-safe
public class Branch {

	private Logger logger = Logger.getLogger(Branch.class.getName());

	// Assigned by head office
	private String branchId;
	private List<BankAccount> bankAccounts;
	private List<Customer> customers;

	AtomicLong lastbankAccountCreated;

	public Branch() {
		bankAccounts = new ArrayList<BankAccount>();
		customers = new ArrayList<Customer>();
		lastbankAccountCreated = new AtomicLong(0L);
	}

	/* Account creation through factory method */
	public synchronized BankAccount createBankAccount(String panNumber, String type, Double amount)
			throws BankAccountException {
		BankAccount newAccount;

		try {
			newAccount = AccountCreationFactory.getInstance(type, amount);
			// setting new accountId
			long newAccountId = lastbankAccountCreated.incrementAndGet();
			newAccount.setAccountNumber(String.valueOf(newAccountId));

		} catch (BankAccountException e) {
			// Re-throwing Account creation Exception
			throw new BankAccountException(logger, e.getMessage());
		}

		// if a customer with the same pan exists, then adding this bank account
		Customer newCustomer = null;
		try {
			newCustomer = getCustomerByPanNumber(panNumber);
			newCustomer.addBankAccount(newAccount);
			customers.add(newCustomer);
			return newAccount;
		} catch (CustomerException e) {
		}

		// Otherwise creating a new customer
		newCustomer = new Customer(panNumber);
		newCustomer.addBankAccount(newAccount);
		customers.add(newCustomer);
		return newAccount;

	}

	/* Searches through the customers registered at this branch using streams */
	public Customer getCustomerByPanNumber(String panNumber) throws CustomerException {

		synchronized (customers) {
			Optional<Customer> customer = customers.parallelStream()
					.filter(e -> e.getPanNumber().equalsIgnoreCase(panNumber)).findAny();

			// throw error that customer is not found
			String errorMsg = "Customer with given pan " + panNumber + " not found in db.";
			customer.orElseThrow(() -> new CustomerException(logger, errorMsg));

			// else return the found customer
			return customer.get();
		}

	}

	/* Searches through the bank accounts registered at this branch using streams */
	public BankAccount getAccountByAccountNumber(String accountNumber) throws BankAccountException {

		synchronized (bankAccounts) {
			Optional<BankAccount> account = bankAccounts.parallelStream()
					.filter(e -> e.getAccountNumber().equalsIgnoreCase(accountNumber)).findAny();

			// throw error that account is not found
			String errorMsg = "Bank Account with given acctNo: " + accountNumber + " not found in db.";
			account.orElseThrow(() -> new BankAccountException(logger, errorMsg));

			// else return the found account
			return account.get();
		}

	}

	public String getBranchId() {
		return branchId;
	}

	public synchronized void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	
	
}
