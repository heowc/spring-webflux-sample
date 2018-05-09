package com.heowc.webflux.service;

import com.heowc.webflux.doamin.Study;
import com.heowc.webflux.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultStudyQueryService implements StudyQueryService {

    private final StudyRepository repository;

    @Autowired
    public DefaultStudyQueryService(StudyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Study> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Study> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Study> add(Study study) {
        return null;
    }

    @Override
    public Mono<Void> modify(Mono<Study> studyMono) {
        return null;
    }

    @Override
    public Mono<Void> remove(String id) {
        return null;
    }
}
