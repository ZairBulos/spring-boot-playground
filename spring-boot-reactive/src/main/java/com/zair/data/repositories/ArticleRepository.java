package com.zair.data.repositories;

import com.zair.data.entities.ArticleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleRepository extends ReactiveMongoRepository<ArticleEntity, String> {
    Mono<ArticleEntity> findByBarcode(String barcode);
    Flux<ArticleEntity> findByProviderEntityId(String providerEntityId);
    Flux<ArticleEntity> findByProviderEntityIsNull();
}
