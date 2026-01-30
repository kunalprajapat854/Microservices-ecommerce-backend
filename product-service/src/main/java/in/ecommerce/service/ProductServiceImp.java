package in.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecommerce.dto.ProductRequest;
import in.ecommerce.dto.ProductResponse;
import in.ecommerce.repository.ProductRepository;
@Service
public class ProductServiceImp  implements ProductService{
	
	@Autowired
	private ProductRepository productRepository ;

	@Override
	public ProductResponse createProduct(ProductRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductResponse updateProduct(Long productId, ProductRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductResponse getProductById(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Long productId) {
		// TODO Auto-generated method stub
		
	}

}
