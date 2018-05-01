package com.heowc.webflux.repository;

import com.heowc.webflux.doamin.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}