package in.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

	@NotBlank(message = "Product name is required")
	private String name;

	@Size(max = 500)
	private String description;

	@NotNull
	@Positive(message = "Price must be positive")
	private Double price;

	@NotNull
	@Min(value = 0, message = "Quantity cannot be negative")
	private Integer quantity;

	@NotBlank(message = "Category is required")
	private String category;

	@Column(nullable = false)
	private Boolean active = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	

}
