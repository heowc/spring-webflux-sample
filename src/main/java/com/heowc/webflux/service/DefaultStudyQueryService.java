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
    public Mono<Study> add(Mono<Study> studyMono) {
        return studyMono.flatMap(repository::save);
    }

    @Override
    public Mono<Study> modify(Mono<Study> studyMono) {
        return studyMono
                .flatMap(s -> repository.findById(s.getId())
                        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect body data")))
                        .map(s1 -> {
                            Study study = new Study();
                            study.setTitle(s.getTitle());
                            study.setContent(s.getContent());
                            return study;
                        }))
                .flatMap(repository::save);
    }

    @Override
    public Mono<Void> remove(String id) {
        return Mono.defer(() -> repository.findById(id))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect param data")))
                .flatMap(repository::delete);
    }
}
