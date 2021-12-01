package designs.vendingMachine;

import java.util.Map;
import java.util.logging.Logger;

import VendingMachine.Exceptions.PaymentNotVerified;
import VendingMachine.Items.Item;
import VendingMachine.Payment.Payment;

public class VendingMachineImpl implements VendingMacines {
	private Logger logger = Logger.getLogger(VendingMacines.class.getName());

	Hardware hardware = new Hardware();

	@Override
	public void distributeItem(Item item) {
		hardware.disburseItem(item);
	}

	@Override
	public void getPayment() throws PaymentNotVerified {
		Payment payment = hardware.fetchPayment();
		if (payment.validatePaymentInput()) {

		} else {
			throw new PaymentNotVerified("Payment not verified", logger);
		}

	}

	@Override
	public Map<Item, Payment> collectItemAndChange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long selectItemAndGetPrice(Item item) {
		// TODO Auto-generated method stub
		return 0;
	}
}
