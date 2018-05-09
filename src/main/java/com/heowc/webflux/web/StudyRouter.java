package com.heowc.webflux.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class StudyRouter {

    @Bean
    public RouterFunction<ServerResponse> routeByStudy(StudyHandler handler) {
        return nest(path("/study"),
                route(GET("/"), handler::findAll)
                .andRoute(GET("/{id}"), handler::findById)
                .andRoute(POST("/").and(accept(MediaType.APPLICATION_JSON_UTF8)), handler::add)
                .andRoute(PUT("/").and(accept(MediaType.APPLICATION_JSON_UTF8)), handler::modify)
                .andRoute(DELETE("/{id}"), handler::remove));
    }
}
