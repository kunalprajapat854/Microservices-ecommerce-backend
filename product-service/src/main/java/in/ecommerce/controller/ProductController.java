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
import in.ecommerce.ProductServiceApplication;
import in.ecommerce.dto.ProductRequest;
import in.ecommerce.dto.ProductResponse;
import in.ecommerce.repository.ProductRepository;
import in.ecommerce.service.ProductService;
import in.ecommerce.service.ProductServiceImp;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/create-products")
	public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequest productRequest) {
		ProductResponse product = productService.createProduct(productRequest);
		return new ResponseEntity<String>("Product Saved", HttpStatus.OK);

	}

	@PutMapping("/{productId}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable("productId") long productId,
			@Valid @RequestBody ProductRequest productRequest) {
		ProductResponse updateProduct = productService.updateProduct(productId, productRequest);
		return ResponseEntity.ok(updateProduct);
	}

	@GetMapping("/{ProductId}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable("ProductId") long ProductId) {
		ProductResponse productById = productService.getProductById(ProductId);
		return ResponseEntity.ok(productById);
	}

	@GetMapping("/all-products")
	public ResponseEntity<List<ProductResponse>> getAllProduct() {
		List<ProductResponse> allProducts = productService.getAllProducts();
		return ResponseEntity.ok(allProducts);
	}

	@DeleteMapping("/{ProductId}")
	public ResponseEntity<String> deleteProducts(@PathVariable("ProductId") long ProductId) {
		productService.deleteProduct(ProductId);
		return ResponseEntity.ok("Product Deleted Successfully");
	}

}
