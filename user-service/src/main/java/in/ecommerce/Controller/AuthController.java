package in.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import in.ecommerce.Service.AuthService;
import in.ecommerce.dto.AuthResponse;
import in.ecommerce.dto.LoginRequest;
import in.ecommerce.dto.RegisterRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request){
		authService.register(request);
		return new ResponseEntity<String>("User registered Successfully", HttpStatusCode.valueOf(200));
		
	}
	
	 @PostMapping("/login")
	    public ResponseEntity<AuthResponse> login(
	            @Valid @RequestBody LoginRequest request) {
		 
		 AuthResponse authResponse = authService.login(request);
		 System.out.println("LOGIN HIT");


	        return ResponseEntity.ok(authResponse);
	    }
	 
	 
	

}
