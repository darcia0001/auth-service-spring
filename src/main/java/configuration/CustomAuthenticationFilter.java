package configuration;


import com.auth0.jwt.interfaces.Claim;
import com.isi.auth_service.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class CustomAuthenticationFilter extends AbstractGatewayFilterFactory<CustomAuthenticationFilter.Config> {

    @Autowired
    private RouterValidator routerValidator;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.prefix}")
    public String TOKEN_PREFIX;

    public CustomAuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
        // Configuration properties if needed
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (routerValidator.isSecured.test(request)) {
                if (this.isAuthMissing(request) || this.isPrefixMissing(request)) {
                    return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
                }

                final String token = this.getAuthHeader(request);

                if (!jwtUtil.validate(token)) {
                    return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
                }

                this.populateRequestWithHeaders(exchange, token);
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        var header = request.getHeaders().getOrEmpty("Authorization").get(0);
        return header.replace(TOKEN_PREFIX, "").trim();
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private boolean isPrefixMissing(ServerHttpRequest request) {
        var header = request.getHeaders().getFirst("Authorization");
        return header == null || !header.startsWith(TOKEN_PREFIX);
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Map<String, Claim> claims = jwtUtil.getClaims(token);
        exchange.getRequest().mutate()
                .header("email", String.valueOf(claims.get("email")))
                .header("id", String.valueOf(claims.get("id")))
                .header("roles", String.valueOf(claims.get("roles")))
                .header("tenantId", String.valueOf(claims.get("tenantId")))
                .build();
    }
}

