package in.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecommerce.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {

	Optional<Roles> findByname(String name);

}
