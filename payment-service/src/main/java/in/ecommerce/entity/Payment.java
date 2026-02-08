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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Payment() {
		super();
	}

	

	@Override
	public String toString() {
		return "Payment [id=" + id + ", orderId=" + orderId + ", paymentMethod=" + paymentMethod + ", amount=" + amount
				+ ", status=" + status + ", paymentDate=" + paymentDate + "]";
	}

	public Payment(Long id, Long orderId, String paymentMethod, Double amount, PaymentStatus status,
			LocalDateTime paymentDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.status = status;
		this.paymentDate = paymentDate;
	}
	
	

}
