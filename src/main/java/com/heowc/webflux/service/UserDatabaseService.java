package com.heowc.webflux.service;

import com.heowc.webflux.doamin.User;
import reactor.core.publisher.Mono;

public interface UserDatabaseService {

    Mono<User> findById(Long id);

    void add(User user);

    void modify(User user);

    void remove(Long id);
}
