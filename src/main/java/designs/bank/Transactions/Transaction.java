package designs.bank.Transactions;

public class Transaction {

	// Assigned by bank account
	private String transactionId;
	// Transaction amount
	private double amount;
	// "withdraw" or "deposit"
	private TransactionType type;

	private Transaction(TransactionBuilder builder) {
		this.transactionId = builder.transactionId;
		this.amount = builder.amount;
		this.type = builder.type;
	}

	public String getTransactionId() {

		return transactionId;
	}

	public double getAmount() {

		return amount;
	}

	public TransactionType getType() {
		return type;
	}

	public static class TransactionBuilder {
		// Assigned by bank account
		private String transactionId;
		// Transaction amount
		private double amount;
		// "withdraw" or "deposit"
		private TransactionType type;

		public TransactionBuilder() {
			// FIXME Auto-generated constructor stub
		}

		public TransactionBuilder setTransactionID(long transactionId) {
			this.transactionId = transactionId + "";
			return this;
		}

		public TransactionBuilder setAmount(Double amount) {
			this.amount = amount;
			return this;
		}

		public TransactionBuilder setType(TransactionType type) {
			this.type = type;
			return this;
		}

		public Transaction build() {
			return new Transaction(this);
		}
	}

}
