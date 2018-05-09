package com.heowc.webflux.service;

import com.heowc.webflux.doamin.User;
import reactor.core.publisher.Mono;

public interface UserQueryService {

    Mono<User> findById(Long id);

    Mono<Void> add(Mono<User> user);

    Mono<Void> modify(Mono<User> user);

    Mono<Void> remove(Long id);
}
