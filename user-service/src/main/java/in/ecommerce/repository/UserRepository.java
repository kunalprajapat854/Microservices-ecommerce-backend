package in.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecommerce.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
