package bank;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import designs.bank.BankAccounts.BankAccount;
import designs.bank.BankAccounts.BankAccountException;
import designs.bank.Branches.Branch;
import designs.bank.Branches.HeadOffice;

public class BankAccountTest {

	static Branch branch;

	@BeforeAll
	static void setupBranch() {
		HeadOffice office = new HeadOffice();
		branch = office.createBranch();
	}

	BankAccount currentAccount;

	@Test
	void Test_Current_UniqueAccountNumber() throws BankAccountException {
		currentAccount = branch.createBankAccount("abc", "Current", 125000.0);
		assertEquals(currentAccount.getAccountNumber(), "1");
	}

	@Test
	void Test_Current_Withdraw_Allowed_Amount() throws BankAccountException {
		currentAccount = branch.createBankAccount("abc", "Current", 125000.0);
		double amount = currentAccount.withdraw(20000);
		assertEquals(105000.0, amount, 0.00001);
	}

	@Test
	void Test_Current_Withdraw_Not_Allowed_Amount() {
		Assertions.assertThrows(BankAccountException.class, () -> Current_Withdraw_Not_Allowed_Amount());
	}

	void Current_Withdraw_Not_Allowed_Amount() throws BankAccountException {
		currentAccount = branch.createBankAccount("abc", "Current", 125000.0);
		double amount = currentAccount.withdraw(120000.0);
	}

	@Test
	void Test_Current_Deposit_Allowed_Amount() throws BankAccountException {
		currentAccount = branch.createBankAccount("abc", "Current", 125000.0);
		double amount = currentAccount.deposit(20000);
		assertEquals(145000.0, amount, 0.00001);
	}

	@Test
	void Test_Current_Deposit_Not_Allowed_Amount() {
		Assertions.assertThrows(BankAccountException.class, () -> Current_Withdraw_Not_Allowed_Amount());
	}

	void Current_Deposit_Not_Allowed_Amount() throws BankAccountException {
		currentAccount = branch.createBankAccount("abc", "Current", 125000.0);
		double amount = currentAccount.deposit(-2000.0);
	}

	@Test
	void Test_Transaction_History() throws BankAccountException {
		currentAccount = branch.createBankAccount("abc", "Current", 125000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.withdraw(1000.0);
		assertEquals(4, currentAccount.getTransactionHistory().size());
	}

	@Test
	void Test_getMiniStatement_TotalTransactions_Less_Than_Ten() throws BankAccountException {
		currentAccount = branch.createBankAccount("abc", "Current", 125000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.withdraw(1000.0);
		assertEquals(4, currentAccount.getMiniStatement().size());
	}

	@Test
	void Test_getMiniStatement_TotalTransactions_More_Than_Ten() throws BankAccountException {
		currentAccount = branch.createBankAccount("abc", "Current", 125000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.withdraw(1000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.withdraw(1000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.withdraw(1000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.deposit(2000.0);
		currentAccount.withdraw(1000.0);
		assertEquals(10, currentAccount.getMiniStatement().size());
	}

}
