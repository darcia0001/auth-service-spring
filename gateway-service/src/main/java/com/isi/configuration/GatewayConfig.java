package com.isi.configuration;

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
        System.out.println("confirguration des regler de routage >>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return builder.routes()


                .route("auth-service", r -> r.path("/api/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://webhook.site/ab447943-baf6-426f-8d30-09d38a370c02"))
                       // .uri("lb://auth-service"))
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8081"))
                        //.uri("lb://user-service"))
                .route(p -> p
                        .path("/moi")
                        .filters(f -> f.filter(filter))

                        //.filters(f -> f.addRequestHeader("Hello", "World GATEWAY"))
                        .uri("http://localhost:8080"))
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.filter(filter))

                       //.filters(f -> f.addRequestHeader("Hello", "World GATEWAY"))
                        .uri("http://localhost:8080"))
                .build();
    }

}


