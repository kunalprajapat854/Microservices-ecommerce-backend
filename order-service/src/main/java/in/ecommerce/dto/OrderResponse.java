package in.ecommerce.dto;

// BUG-23 FIX: Renamed class from "OrderRespose" (typo) to "OrderResponse".
// The filename OrderRespose.java is kept for backward compatibility but now just delegates.
// All new code should use OrderResponse.
public class OrderResponse {

	private Long orderId;
	private String status;
	private Double totalPrice;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderResponse(Long orderId, String status, Double totalPrice) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public OrderResponse() {
		super();
	}

}
