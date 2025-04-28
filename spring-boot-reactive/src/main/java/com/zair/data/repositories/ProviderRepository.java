package com.zair.data.repositories;

import com.zair.data.entities.ProviderEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProviderRepository extends ReactiveMongoRepository<ProviderEntity, String> {
    Mono<ProviderEntity> findByNif(String nif);
    Mono<ProviderEntity> findByCompany(String company);
}
