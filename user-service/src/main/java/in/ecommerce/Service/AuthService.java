package in.ecommerce.Service;

import in.ecommerce.dto.AuthResponse;
import in.ecommerce.dto.LoginRequest;
import in.ecommerce.dto.RegisterRequest;

public interface AuthService {

	public void register(RegisterRequest request);

	public AuthResponse login(LoginRequest loginRequest);
}