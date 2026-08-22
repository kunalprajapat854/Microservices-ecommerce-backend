package in.ecommerce.dto;

import in.ecommerce.Enum.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
	
	private Long paymentId;
	private Long orderId;
	private PaymentStatus status;
	private String message;

}