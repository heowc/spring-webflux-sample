package com.heowc.webflux.web;

import com.heowc.webflux.doamin.User;
import com.heowc.webflux.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Configuration
public class UserHandler {

    private final UserQueryService service;

    @Autowired
    public UserHandler(UserQueryService service) {
        this.service = service;
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        try {
            String id = request.pathVariable("id");
            return ServerResponse.ok().body(service.findById(Long.valueOf(id)), User.class);
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().build();
        }
    }

    public Mono<ServerResponse> add(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect body data")));
        return ServerResponse.ok().body(service.add(userMono), User.class);
    }

    public Mono<ServerResponse> modify(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect body data")));
        return ServerResponse.ok().body(service.modify(userMono), User.class);
    }

    public Mono<ServerResponse> remove(ServerRequest request) {
        try {
            String id = request.pathVariable("id");
            return ServerResponse.ok().build(service.remove(Long.valueOf(id)));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().build();
        }
    }
}
