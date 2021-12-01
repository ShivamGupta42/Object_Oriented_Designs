package designs.ecommerce;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

	HashMap<InventoryProduct, Integer> productQuanityMap;

	public Inventory() {
		productQuanityMap = new HashMap<>();
	}

	void addProduct(InventoryProduct prod, int quantity) {
		productQuanityMap.put(prod, quantity);
	}

	void removeProduct(InventoryProduct prod) {
		productQuanityMap.remove(prod);
	}

	void refillQuanitty(InventoryProduct product, int addQuantity) {
		productQuanityMap.computeIfPresent(product, (k, v) -> v + addQuantity);
		product.notifyCartsToAddQuantity(addQuantity);
	}

	void reduceQuantity(InventoryProduct product, int decreaseQuantity) {
		productQuanityMap.computeIfPresent(product, (k, v) -> v - decreaseQuantity);
		product.notifyCartsTodecreaseQuantity(1);
	}

	List<Product> searchProduct(String productName) {
		return productQuanityMap.entrySet().parallelStream().filter(e -> e.getKey().getP().name.contains(productName))
				.map(e -> e.getKey().getP()).collect(Collectors.toList());
	}
}
