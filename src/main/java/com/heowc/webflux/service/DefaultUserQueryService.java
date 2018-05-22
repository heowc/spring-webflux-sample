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
public class DefaultUserQueryService implements UserQueryService {

    private final UserRepository repository;

    @Autowired
    public DefaultUserQueryService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<User> findById(Long id) {
        return Mono.defer(() -> Mono.justOrEmpty(repository.findById(id)))
                .subscribeOn(Schedulers.newParallel("find-sub"));
    }

    @Override
    public Mono<User> add(Mono<User> userMono) {
        return userMono//.publishOn(Schedulers.newParallel("add-pub"))
                .map(repository::save);
    }

    @Override
    public Mono<User> modify(Mono<User> userMono) {
        return userMono//.publishOn(Schedulers.newParallel("modify-pub"))
                .flatMap(u ->
                        Mono.justOrEmpty(repository.findById(u.getId()))
                                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "not exist")))
                                .map(u2 -> {
                                    u2.setFullName(u.getFullName());
                                    u2.setAge(u.getAge());
                                    u2.setFullAddress(u.getFullAddress());
                                    return repository.save(u2);
                                })
                );
    }

    public Mono<Void> remove(Long id) {
        return Mono.just(id)//.publishOn(Schedulers.newParallel("remove-pub"))
                .flatMap(i ->
                        Mono.justOrEmpty(repository.findById(i))
                                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "not exist")))
                                .flatMap(u -> Mono.create(c -> repository.delete(u)))
                );

    }
}
