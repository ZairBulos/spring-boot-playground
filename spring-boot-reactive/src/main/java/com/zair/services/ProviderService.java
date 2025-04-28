package com.zair.services;

import com.zair.api.dtos.Provider;
import reactor.core.publisher.Mono;

public interface ProviderService {
    public Mono<Provider> read(String company);
    public Mono<Provider> create(Provider provider);
    public Mono<Provider> update(String company, Provider provider);
}
