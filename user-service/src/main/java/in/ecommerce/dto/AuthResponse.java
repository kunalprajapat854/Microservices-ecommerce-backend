package in.ecommerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthResponse {

	private String token;

	private String type = "Bearer";

	private String email;

	private List<String> roles;

	public AuthResponse(String token, String type, String email, List<String> roles) {
		super();
		this.token = token;
		this.type = type;
		this.email = email;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
