package in.ecommerce.dto;

public class OrderRespose {

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

	public OrderRespose(Long orderId, String status, Double totalPrice) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.totalPrice = totalPrice;
	}

}
