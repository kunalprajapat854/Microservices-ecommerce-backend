package in.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * BUG-07 FIX: Added @Transactional to prevent race conditions (concurrent orders overselling stock).
	 * The read-check-write sequence is now wrapped in a single DB transaction with implicit locking.
	 *
	 * BUG-08 FIX (inventory side): This method now throws "Insufficient stock" if the requested
	 * quantity exceeds available stock, eliminating the need for a separate getInventory() check
	 * in order-service before calling this. Order-service now calls reduceStock() directly.
	 */
	@Override
	@Transactional
	public void reduceStock(long productId, Integer quantity) {
		Inventory inventory = repository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Inventory not found for productId: " + productId));

		if (inventory.getQuantity() < quantity) {
			throw new RuntimeException(
				"Insufficient stock for productId: " + productId +
				". Requested: " + quantity + ", Available: " + inventory.getQuantity()
			);
		}

		inventory.setQuantity(inventory.getQuantity() - quantity);
		inventory.setInStock(inventory.getQuantity() > 0);

		repository.save(inventory);
	}

}
