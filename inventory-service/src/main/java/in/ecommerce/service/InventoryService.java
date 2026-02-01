package in.ecommerce.service;

import in.ecommerce.dto.InventoryRequest;
import in.ecommerce.dto.InventoryResponse;

public interface InventoryService {
	
	InventoryResponse addInventory(InventoryRequest  request);
	
	InventoryResponse getInventoryByProductId(Long productId);
	
	void reduceStock(long prouductId , Integer quantity);
	
	

}
