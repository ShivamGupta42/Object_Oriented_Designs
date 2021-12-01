package designs.ecommerce;

import java.util.List;

public class UserInventoryFacade {
	Inventory inventory;

	UserInventoryFacade(Inventory inventory) {
		this.inventory = inventory;
	}

	List<Product> searchProduct(String name) {
		return inventory.searchProduct(name);
	}

}
