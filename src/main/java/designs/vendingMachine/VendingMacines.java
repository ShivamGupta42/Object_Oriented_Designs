package designs.vendingMachine;

import java.util.List;
import java.util.Map;

import designs.vendingMachine.Exceptions.PaymentNotVerified;
import designs.vendingMachine.Items.Item;
import designs.vendingMachine.Payment.Payment;

public interface VendingMacines {

	void getPayment() throws PaymentNotVerified;

	void distributeItem(Item item);

	long selectItemAndGetPrice(Item item);

	Map<Item, Payment> collectItemAndChange();

}
