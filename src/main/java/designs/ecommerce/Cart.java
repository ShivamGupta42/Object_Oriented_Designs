package designs.ecommerce;

import java.util.HashMap;
import java.util.List;

//Observer
public class Cart implements InventoryProductObservers {
	HashMap<InventoryProduct, Integer> products;

	void addInventoryProduct(InventoryProduct product, int quantity) {
		products.put(product, quantity);
		product.RegisterObserver(this);
	}

	void removeInventoryProduct(InventoryProduct product) {
		products.remove(product);
		product.DeRegisterObserver(this);
	}

	@Override
	public void notificationToRemoveProduct(InventoryProduct product) {
		products.computeIfPresent(product, (k, v) -> v - 1);
	}

	@Override
	public void notificationToAddProduct(InventoryProduct product) {
		products.computeIfPresent(product, (k, v) -> v + 1);

	}

}
