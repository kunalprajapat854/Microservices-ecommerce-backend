package in.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecommerce.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

    // Fetch only active products

	List<Products> findByActiveTrue();

}
