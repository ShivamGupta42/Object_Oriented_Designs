package designs.bank.BankAccounts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import designs.bank.Transactions.Transaction;
import designs.bank.Transactions.TransactionType;
import designs.bank.Transactions.Transaction.TransactionBuilder;

//Thread-safe bank account class
public abstract class BankAccount {

	private Logger logger = Logger.getLogger(BankAccount.class.getName());

	// Unique number generated by branch
	private String accountNumber;

	// Constraint on current balance
	private double minimumBalance;

	// Current Amount
	private double currentBalance;

	// Percent Interest
	private double interestRate;

	// List of all transactions -Latest transaction is at the end of list.
	private List<Transaction> transactions;

	// Automatic generation of Transaction Ids
	AtomicLong currrentTransactionId;

	public BankAccount(double amount, double minimumBalance, double interestRate) {
		transactions = new ArrayList<Transaction>();
		currrentTransactionId = new AtomicLong(0L);
		currentBalance = amount;
		this.minimumBalance = minimumBalance;
		this.interestRate = interestRate;
	}

	public synchronized Double withdraw(double amount) throws BankAccountException {

		if (currentBalance - amount >= minimumBalance) {
			long curId = currrentTransactionId.incrementAndGet();
			Transaction newTransaction = new TransactionBuilder().setTransactionID(curId).setAmount(amount)
					.setType(TransactionType.WITHDRAW).build();
			transactions.add(newTransaction);
			currentBalance -= amount;

		} else {
			String errorMsg = "Withdrawl of amount : " + amount + " is declined due to insufficient minimum balance";
			throw new BankAccountException(logger, errorMsg);
		}
		return currentBalance;
	}

	public synchronized Double deposit(double amount) throws BankAccountException {

		if (amount < 0) {
			String errorMsg = "Deposit of negative amount : " + amount + " is now supported.";
			throw new BankAccountException(logger, errorMsg);
		}

		long curId = currrentTransactionId.incrementAndGet();
		Transaction newTransaction = new TransactionBuilder().setTransactionID(curId).setAmount(amount)
				.setType(TransactionType.DEPOSIT).build();
		transactions.add(newTransaction);
		currentBalance += amount;
		return currentBalance;

	}

	public List<Transaction> getTransactionHistory() {
		return new ArrayList<Transaction>(transactions);
	}

	public List<Transaction> getMiniStatement() {
		List<Transaction> lastTenTransactions = null;
		if (transactions.size() >= 10) {
			lastTenTransactions = new ArrayList<Transaction>(
					transactions.subList(transactions.size() - 10, transactions.size()));
		} else {
			lastTenTransactions = new ArrayList<Transaction>(transactions);
		}
		return lastTenTransactions;
	}

	public Double getMinimumBalance() {
		return minimumBalance;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	abstract String getType();

}
