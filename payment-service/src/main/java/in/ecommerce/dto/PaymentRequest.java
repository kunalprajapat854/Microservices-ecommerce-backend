package in.ecommerce.dto;

import lombok.Data;

/**
 * BUG-18 FIX: Removed manually-written getters and setters that duplicated
 * what Lombok's @Data already generates. Duplicate methods cause compile errors.
 */
@Data
public class PaymentRequest {

	private Long orderId;
	private Double amount;
	private String paymentMethod;

}
