package in.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.ecommerce.ProductServiceApplication;
import in.ecommerce.dto.ProductRequest;
import in.ecommerce.dto.ProductResponse;
import in.ecommerce.entity.Products;
import in.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

	private final ProductServiceApplication productServiceApplication;

	@Autowired
	private ProductRepository productRepository;

	ProductServiceImp(ProductServiceApplication productServiceApplication) {
		this.productServiceApplication = productServiceApplication;
	}

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
		System.out.println("PRODUCT FROM DB: " + product);

		return response;

	}

	@Override
	public ProductResponse updateProduct(Long productId, ProductRequest request) {
		Products product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		product.setName(request.getName());
		product.setPrice(request.getPrice());
		product.setCategory(request.getCategory());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setActive(request.getActive());

		Products updateProduct = productRepository.save(product);
		return mapToResponse(updateProduct);

	}

	@Override
	public ProductResponse getProductById(Long ProductId) {
		Products product = productRepository.findById(ProductId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		return mapToResponse(product);
	}

	@Override
	public List<ProductResponse> getAllProducts() {

		return productRepository.findByActiveTrue().stream().map(this::mapToResponse).toList();

	}

	public void deleteProduct(Long ProductId) {
		Products products = productRepository.findById(ProductId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		// soft delete
		products.setActive(false);
		productRepository.save(products);
	}

}
