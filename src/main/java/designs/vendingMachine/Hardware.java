package designs.vendingMachine;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import designs.vendingMachine.Items.Item;
import designs.vendingMachine.Payment.Payment;
import designs.vendingMachine.Payment.UPIPayment;

public class Hardware {

	static TransactionStatus status = TransactionStatus.WAITING;

	Lock hardwareLock = new ReentrantLock();
	Condition PaymentFinished = hardwareLock.newCondition();

	public Payment fetchPayment() {
		hardwareLock.lock();

		while (status != TransactionStatus.WAITING) {
			hardwareLock.unlock();
			hardwareLock.lock();
		}
		
		Payment payment = null;
	  
		payment = new UPIPayment();

		hardwareLock.unlock();
		return payment;
	}

	public Item disburseItem(Item item) {
		System.out.println("Please collect :" + item.getName());
		return item;
	}

	static void changeStatus(TransactionStatus newStatus) {
		synchronized (Hardware.class) {
			status = newStatus;
		}
	}

}
