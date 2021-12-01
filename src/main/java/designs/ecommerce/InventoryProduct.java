package designs.ecommerce;

import java.util.List;

//Subject
public class InventoryProduct {

	Product product;

	public InventoryProduct(Product p) {
		this.product = p;
	}

	List<InventoryProductObservers> observers;

	void RegisterObserver(InventoryProductObservers observer) {
		observers.add(observer);
	}

	void DeRegisterObserver(InventoryProductObservers observer) {
		observers.removeIf(e -> e.equals(observer));
	}

	void notifyCartsToAddQuantity(int increasedQuantity) {
		observers.forEach(o -> o.notificationToAddProduct(this));
	}

	void notifyCartsTodecreaseQuantity(int decreasedQuantity) {
		observers.forEach(o -> o.notificationToRemoveProduct(this));
	}

	public Product getP() {
		return product;
	}

	public void setP(Product p) {
		this.product = p;
	}

}
