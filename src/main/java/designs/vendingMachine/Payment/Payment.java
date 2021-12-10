package designs.vendingMachine.Payment;

import designs.vendingMachine.Hardware;

public interface Payment {

	boolean validatePaymentInput();

	boolean compltePaymentProcess();
}
