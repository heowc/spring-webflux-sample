package com.heowc.webflux.web;

import com.heowc.webflux.doamin.Study;
import com.heowc.webflux.doamin.User;
import com.heowc.webflux.service.StudyQueryService;
import com.heowc.webflux.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.util.List;

@Configuration
public class StudyHandler {

    private final StudyQueryService service;

    @Autowired
    public StudyHandler(StudyQueryService service) {
        this.service = service;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(BodyInserters.fromPublisher(service.findAll(), Study.class));
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        try {
            String id = request.pathVariable("id");
            return ServerResponse.ok().body(BodyInserters.fromPublisher(service.findById(id), Study.class));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().build();
        }
    }

    public Mono<ServerResponse> add(ServerRequest request) {
        request.bodyToMono(Study.class)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect body data")))
                .subscribe(service::add);
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> modify(ServerRequest request) {
        Mono<Study> studyMono = request.bodyToMono(Study.class)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect body data")));
        return ServerResponse.ok().build(service.modify(studyMono));
    }

    public Mono<ServerResponse> remove(ServerRequest request) {
        try {
            String id = request.pathVariable("id");
            return ServerResponse.ok().build(service.remove(id));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().build();
        }
    }
}
