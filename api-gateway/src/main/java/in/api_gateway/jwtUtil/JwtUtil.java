package in.api_gateway.jwtUtil;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String secret;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("JWT secret is not configured!");
        }
        this.secret = secret;
    }

    public void validateToken(String token) {
        Jwts.parserBuilder()
                // BUG-22 FIX: Use explicit StandardCharsets.UTF_8 to ensure consistent behaviour
                // across all JVMs, matching user-service which also uses UTF_8.
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token);
    }

    // BUG-10 FIX: Removed @PostConstruct checkSecret() method that was printing the JWT
    // signing secret to application logs — a serious security vulnerability.

}
