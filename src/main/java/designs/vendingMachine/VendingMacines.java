package designs.vendingMachine;

import java.util.List;
import java.util.Map;

import VendingMachine.Exceptions.PaymentNotVerified;
import VendingMachine.Items.Item;
import VendingMachine.Payment.Payment;

public interface VendingMacines {

	void getPayment() throws PaymentNotVerified;

	void distributeItem(Item item);

	long selectItemAndGetPrice(Item item);

	Map<Item, Payment> collectItemAndChange();

}
