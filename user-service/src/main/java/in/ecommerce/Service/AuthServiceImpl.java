package in.ecommerce.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.ecommerce.Security.JwtService;
import in.ecommerce.dto.AuthResponse;
import in.ecommerce.dto.LoginRequest;
import in.ecommerce.dto.RegisterRequest;
import in.ecommerce.entity.Roles;
import in.ecommerce.entity.User;
import in.ecommerce.repository.RolesRepository;
import in.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private PasswordEncoder encoder ;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	public void register(RegisterRequest request) {

	    if (repository.findByEmail(request.getEmail()).isPresent()) {
	        throw new RuntimeException("Email already registered");
	    }

	    Roles usrRole = rolesRepository.findByname("ROLE_USER")
	            .orElseThrow(() -> new RuntimeException("Role not found"));

	    User user = new User();
	    user.setUsername(request.getUsername());
	    user.setEmail(request.getEmail());
	    user.setPassword(encoder.encode(request.getPassword()));
	    user.setEnabled(true);
	    user.setRoles(Set.of(usrRole));

	    repository.save(user);
	}


	public AuthResponse login(LoginRequest loginRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		User user = repository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		String token = jwtService.generateToken(user);

		  String tokenString = jwtService.generateToken(user);
		  System.out.println("TOKEN GENERATED: " + token);


	        return new AuthResponse(
	                token,
	                "Bearer",
	                user.getEmail(),
	                user.getRoles()
	                        .stream()
	                        .map(Roles::getName)
	                        .toList()
	        );
	    }

	
	
	

	}
