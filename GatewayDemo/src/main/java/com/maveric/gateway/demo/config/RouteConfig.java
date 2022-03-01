package com.maveric.gateway.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()

                .route("Customer", rt -> rt.path("/CustomerCreation/**")
                        .uri("http://localhost:8085/"))
                .route("Account", rt -> rt.path("/AccountCreation/**")
                        .uri("http://localhost:8086/"))
                .build();
    }
}
