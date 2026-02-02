package in.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecommerce.entity.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(Long userId);

}
