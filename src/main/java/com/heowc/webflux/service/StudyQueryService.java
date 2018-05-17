package com.heowc.webflux.service;

import com.heowc.webflux.doamin.Study;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudyQueryService {

    Flux<Study> findAll();

    Mono<Study> findById(String id);

    Mono<Study> add(Mono<Study> study);

    Mono<Study> modify(Mono<Study> studyMono);

    Mono<Void> remove(String id);
}
