package com.heowc.webflux.repository;

import com.heowc.webflux.doamin.Study;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StudyRepository extends ReactiveMongoRepository<Study, String> {
}
