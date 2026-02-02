package in.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ecommerce.dto.InventoryResponse;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

	@GetMapping("/inventory/{productId}")
	InventoryResponse getInventory(@PathVariable Long productId);

	@PutMapping("/inventory/reduce")
	void reduceStock(@RequestParam Long productId, @RequestParam Integer quantity);
}
