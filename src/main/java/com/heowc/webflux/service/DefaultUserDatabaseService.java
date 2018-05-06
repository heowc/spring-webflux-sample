package com.heowc.webflux.service;

import com.heowc.webflux.doamin.User;
import com.heowc.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class DefaultUserDatabaseService implements UserDatabaseService {

    private final UserRepository repository;

    @Autowired
    public DefaultUserDatabaseService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<User> findById(Long id) {
        return Mono.defer(() -> Mono.justOrEmpty(repository.findById(id)))
                .log()
                .subscribeOn(Schedulers.newParallel("find-sub"));
    }

    @Override
    public Mono<Void> add(Mono<User> user) {
            return user.publishOn(Schedulers.newParallel("add-pub"))
                    .log()
                    .doOnNext(repository::save)
                    .then();
    }

    @Override
    public Mono<Void> modify(Mono<User> userMono) {
        return userMono.publishOn(Schedulers.newParallel("modify-pub"))
                .flatMap(u -> Mono.justOrEmpty(repository.findById(u.getId())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "not exist")))
                .log()
                .doOnNext(repository::save)
                .then();
    }

    public Mono<Void> remove(Long id) {
        return Mono.just(id)
                .publishOn(Schedulers.newParallel("remove-pub"))
                .flatMap(i -> Mono.justOrEmpty(repository.findById(i)))
                .log()
                .doOnNext(repository::delete)
                .then();
    }
}
