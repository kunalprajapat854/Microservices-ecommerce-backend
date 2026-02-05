package in.ecommerce.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.ecommerce.Service.UserDetailServiceImp;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	@Autowired
	private UserDetailServiceImp detailServiceImp;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http)  throws Exception{
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth -> 
		auth.requestMatchers("/auth/**" , "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html"
)
		.permitAll()
	   .requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
				)
		
		 .sessionManagement(session ->
         session.sessionCreationPolicy(
             SessionCreationPolicy.STATELESS
         )	
     )
		 .userDetailsService(detailServiceImp)
		 .addFilterBefore(authenticationFilter , UsernamePasswordAuthenticationFilter.class );
		return http.build();
	}
	
	
//	Password encoder
	@Bean
	public PasswordEncoder encoder () {
		return new BCryptPasswordEncoder();
	}
	
	
//	Authentication Manager
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager() ;
		
	}
	

}
