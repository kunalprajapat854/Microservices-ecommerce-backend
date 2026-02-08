package in.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecommerce.entity.Payment;
import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

	Optional<Payment> findByOrderId(Long orderId);

}
