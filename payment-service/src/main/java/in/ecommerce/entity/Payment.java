package in.ecommerce.entity;

import java.time.LocalDateTime;

import in.ecommerce.Enum.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BUG-17 FIX: Removed all manually-written getters, setters, constructors, and toString()
 * that were duplicating what Lombok's @Data/@NoArgsConstructor/@AllArgsConstructor/@Builder
 * already generates. Duplicate method definitions cause compile errors.
 */
@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long orderId;

	private String paymentMethod; // CARD, UPI, NET_BANKING

	private Double amount;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	private LocalDateTime paymentDate;

}
