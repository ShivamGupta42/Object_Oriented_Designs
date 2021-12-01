package designs.ecommerce;

public interface InventoryProductObservers {

	void notificationToRemoveProduct(InventoryProduct product);

	void notificationToAddProduct(InventoryProduct product);

}
