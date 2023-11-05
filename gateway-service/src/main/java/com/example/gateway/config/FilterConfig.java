package com.example.gateway.config;


import com.example.gateway.filter.CustomFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class FilterConfig {
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, CustomFilter customFilter) {
//        return builder.routes()
//                .route(r -> r.path("/first-service/**")
//                        .filters(f -> f.addRequestHeader("first-request", "first-request")
//                                        .addResponseHeader("first-response", "first-response")
//                                .filter(customFilter.apply(new CustomFilter.Config()))
//                        )
//                        .uri("http://localhost:8081"))
//                .build();
//    }
//}
