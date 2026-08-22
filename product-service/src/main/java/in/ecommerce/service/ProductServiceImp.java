package in.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecommerce.dto.ProductRequest;
import in.ecommerce.dto.ProductResponse;
import in.ecommerce.entity.Products;
import in.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

	// BUG-04 FIX: Removed unnecessary injection of ProductServiceApplication (main class) into this service.
	// The field was never used, and caused a constructor conflict with @RequiredArgsConstructor.

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductResponse createProduct(ProductRequest request) {
		Products product = new Products();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setQuantity(request.getQuantity());
		product.setCategory(request.getCategory());
		product.setActive(true);
		Products savedProducts = productRepository.save(product);
		return mapToResponse(savedProducts);
	}

	private ProductResponse mapToResponse(Products product) {
		ProductResponse response = new ProductResponse();
		response.setId(product.getId());
		response.setName(product.getName());
		response.setDescription(product.getDescription());
		response.setPrice(product.getPrice());
		response.setQuantity(product.getQuantity());
		response.setCategory(product.getCategory());
		response.setActive(product.getActive());
		return response;
	}

	@Override
	public ProductResponse updateProduct(Long productId, ProductRequest request) {
		Products product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setCategory(request.getCategory());
		product.setQuantity(request.getQuantity());
		product.setActive(request.getActive());

		// BUG-16 FIX: Removed duplicate setPrice call. Price is now set exactly once.
		product.setPrice(request.getPrice());

		Products updateProduct = productRepository.save(product);
		return mapToResponse(updateProduct);
	}

	@Override
	public ProductResponse getProductById(Long productId) {
		Products product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		return mapToResponse(product);
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		return productRepository.findByActiveTrue().stream().map(this::mapToResponse).toList();
	}

	public void deleteProduct(Long productId) {
		Products products = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		// soft delete
		products.setActive(false);
		productRepository.save(products);
	}

}
