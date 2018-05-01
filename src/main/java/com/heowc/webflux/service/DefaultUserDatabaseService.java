package com.heowc.webflux.service;

import com.heowc.webflux.doamin.User;
import com.heowc.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Transactional
public class DefaultUserDatabaseService implements UserDatabaseService {

    private final UserRepository repository;

    @Autowired
    public DefaultUserDatabaseService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<User> findById(Long id) {
        return Mono.defer(() -> Mono.just(repository.findById(id).orElse(new User())))
                .log()
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "not exist")))
                .subscribeOn(Schedulers.newParallel("find-sub"));
    }

    @Override
    public void add(User user) {
        Mono.defer(() -> Mono.just(repository.save(user)))
            .log()
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "db error")))
            .publishOn(Schedulers.newParallel("add-pub"))
            .doOnNext(repository::save)
            .subscribe();
    }

    @Override
    public void modify(User user) {
        findById(user.getId())
            .map(u -> {
                u.setFullName(user.getFullName());
                u.setAge(user.getAge());
                u.setFullAddress(user.getFullAddress());
                return u;
            })
            .publishOn(Schedulers.newParallel("modify-pub"))
            .doOnNext(repository::save)
            .subscribe();
    }

    public void remove(Long id) {
        findById(id)
            .publishOn(Schedulers.newParallel("remove-pub"))
            .doOnNext(repository::delete)
            .subscribe();
    }
}
