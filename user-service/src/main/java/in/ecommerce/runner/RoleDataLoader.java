package in.ecommerce.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.ecommerce.entity.Roles;
import in.ecommerce.repository.RolesRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleDataLoader implements CommandLineRunner {

	@Autowired
	private RolesRepository rolesRepository;

	@Override
	public void run(String... args) throws Exception {

		if (rolesRepository.findByname("ROLE_USER").isEmpty()) {
			Roles rolesUser = new Roles();
			rolesUser.setName("ROLE_USER");
			rolesRepository.save(rolesUser);
		}

		if (rolesRepository.findByname("ROLE_ADMIN").isEmpty()) {
			Roles rolesUser = new Roles();
			rolesUser.setName("ROLE_ADMIN");
			rolesRepository.save(rolesUser);
		}

		System.out.println("Roles loaded successfully !");

	}

}
