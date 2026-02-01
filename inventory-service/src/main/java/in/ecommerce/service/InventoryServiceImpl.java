package in.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecommerce.dto.InventoryRequest;
import in.ecommerce.dto.InventoryResponse;
import in.ecommerce.entity.Inventory;
import in.ecommerce.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository repository;

	@Override
	public InventoryResponse addInventory(InventoryRequest request) {
		Inventory inventory = new Inventory();
		inventory.setProductId(request.getProductId());
		inventory.setQuantity(request.getQuantity());
		inventory.setInStock(request.getQuantity() > 0);

		Inventory savedInventory = repository.save(inventory);

		return new InventoryResponse(savedInventory.getProductId(), savedInventory.getQuantity(),
				savedInventory.getInStock());
	}

	@Override
	public InventoryResponse getInventoryByProductId(Long productId) {
		Inventory inventory = repository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Inventory not found"));

		return new InventoryResponse(inventory.getProductId(), inventory.getQuantity(), inventory.getInStock());
	}

	@Override
	public void reduceStock(long prouductId, Integer quantity) {
		Inventory inventory = repository.findByProductId(prouductId)
				.orElseThrow(() -> new RuntimeException("Inventory not found"));

		if (inventory.getQuantity() < quantity) {
			throw new RuntimeException("Insufficient stock");
		}

		inventory.setQuantity(inventory.getQuantity() - quantity);
		inventory.setInStock(inventory.getQuantity() > 0);

		repository.save(inventory);

	}

}
