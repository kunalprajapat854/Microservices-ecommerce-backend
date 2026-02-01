package in.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecommerce.entity.Inventory;
import java.util.Optional;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	Optional<Inventory> findByProductId(Long productId);

}
