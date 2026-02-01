package in.ecommerce.dto;

public class InventoryResponse {

	private Long productId;
	private Integer quantity;
	private Boolean inStock;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	public InventoryResponse(Long productId, Integer quantity, Boolean inStock) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.inStock = inStock;
	}

}
