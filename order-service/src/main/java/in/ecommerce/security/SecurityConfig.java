package in.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity   // REQUIRED for @PreAuthorize
public class SecurityConfig {

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth
                // Swagger endpoints - PUBLIC
                .requestMatchers(SWAGGER_WHITELIST).permitAll()

                // Optional: Actuator
                .requestMatchers("/actuator/**").permitAll()

                // Order APIs - secured
                .requestMatchers(HttpMethod.POST, "/orders/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/orders/**").hasRole("USER")

                // Everything else
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
