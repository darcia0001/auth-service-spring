package configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))
                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri("lb://auth-service"))
                .build();
    }

}
/*

@Configuration
public class GatewayConfig {
    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }
}


/*
@Configuration
public class GatewayConfig {
    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/data/**")
                        .filters(f -> f.filter((GatewayFilter) filter))
                        .uri("lb://user-service"))
                .route("user-service", r -> r.path("/api/users/**")
                        .filters(f -> f.filter((GatewayFilter) filter))
                        .uri("lb://user-service"))
                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri("lb://auth-service"))
                .build();
    }

}
*/

