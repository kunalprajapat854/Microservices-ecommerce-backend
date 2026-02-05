package in.api_gateway.jwtUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

	@Autowired
	private JwtUtil jwtutil;
	
	
	 private static final List<String> OPEN_API_ENDPOINTS = List.of(
			     "/auth/",
			    "/swagger-ui",
			    "/swagger-ui/",
			    "/swagger-ui.html",
			    "/v3/api-docs",
			    "/v3/api-docs/",
			    "/user-service/v3/api-docs",
			    "/product-service/v3/api-docs",
			    "/inventory-service/v3/api-docs",
			    "/order-service/v3/api-docs"
			);
	    

	@Override
	public int getOrder() {
		return -1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String path = exchange.getRequest().getURI().getPath();

		// Public APIs
		if (isOpenEndpoint(path)) {
			return chain.filter(exchange);
		}

		String authheader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (authheader == null || !authheader.startsWith("Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}

		String token = authheader.substring(7);
		try {
			jwtutil.validateToken(token);
		} catch (Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}

		return chain.filter	(exchange);
	}
	
	 private boolean isOpenEndpoint(String path) {
	        return OPEN_API_ENDPOINTS.stream().anyMatch(path::startsWith);
	    }

}
