package in.ecommerce.service;

import java.util.List;

import in.ecommerce.dto.ProductRequest;
import in.ecommerce.dto.ProductResponse;

public interface ProductService {

	ProductResponse createProduct(ProductRequest request);

	ProductResponse updateProduct(Long productId, ProductRequest request);

	ProductResponse getProductById(Long productId);

	List<ProductResponse> getAllProducts();

	void deleteProduct(Long productId);

}
