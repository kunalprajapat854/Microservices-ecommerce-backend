package in.ecommerce.config;

// BUG-14 FIX: @EnableMethodSecurity was declared in both this class and SecurityConfig.
// Removed the duplicate annotation here. Method security is now enabled only in SecurityConfig.
// This class is kept to avoid git history loss but has no functional role.
public class MethodSecurityConfig {
}
