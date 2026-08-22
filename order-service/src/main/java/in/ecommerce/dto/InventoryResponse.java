package in.ecommerce.dto;

// BUG-19 FIX: Removed @Getter/@Setter from Lombok alongside manually-written accessors.
// Keeping only manual getters/setters to avoid duplicate method compile errors.
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

	public InventoryResponse() {
		super();
	}

	public InventoryResponse(Long productId, Integer quantity, Boolean inStock) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.inStock = inStock;
	}

}
