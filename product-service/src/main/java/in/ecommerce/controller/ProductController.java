package in.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecommerce.dto.ProductRequest;
import in.ecommerce.dto.ProductResponse;
import in.ecommerce.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * BUG-15 FIX: Previously this endpoint discarded the created product and returned a plain String,
	 * meaning callers had no way to retrieve the new product's generated ID.
	 * Now returns the full ProductResponse with HTTP 201 Created.
	 */
	@PostMapping("/create-products")
	public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
		ProductResponse product = productService.createProduct(productRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	@PutMapping("/{productId:[0-9]+}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable("productId") long productId,
			@Valid @RequestBody ProductRequest productRequest) {
		ProductResponse updateProduct = productService.updateProduct(productId, productRequest);
		return ResponseEntity.ok(updateProduct);
	}

	@GetMapping("/{productId:[0-9]+}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") long productId) {
		ProductResponse productById = productService.getProductById(productId);
		return ResponseEntity.ok(productById);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ProductResponse>> getAllProduct() {
		List<ProductResponse> allProducts = productService.getAllProducts();
		return ResponseEntity.ok(allProducts);
	}

	@DeleteMapping("/{productId:[0-9]+}")
	public ResponseEntity<String> deleteProducts(@PathVariable("productId") Long productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.ok("Product Deleted Successfully");
	}

}
