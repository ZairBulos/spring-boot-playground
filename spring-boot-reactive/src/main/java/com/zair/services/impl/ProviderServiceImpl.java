package com.zair.services.impl;

import com.zair.api.dtos.Provider;
import com.zair.data.entities.ProviderEntity;
import com.zair.data.repositories.ProviderRepository;
import com.zair.services.ProviderService;
import com.zair.services.exceptions.ConflictException;
import com.zair.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;

    @Override
    public Mono<Provider> read(String company) {
        return this.providerRepository
                .findByCompany(company)
                .switchIfEmpty(Mono.error(new NotFoundException("Non existent company: " + company)))
                .map(ProviderEntity::toProvider);
    }

    @Override
    public Mono<Provider> create(Provider provider) {
        return this
                .assertNifAndCompanyNotExist(provider.getNif(), provider.getCompany())
                .then(this.providerRepository.save(new ProviderEntity(provider)))
                .map(ProviderEntity::toProvider);
    }

    @Override
    public Mono<Provider> update(String company, Provider provider) {
        return this.providerRepository
                .findByCompany(company)
                .switchIfEmpty(Mono.error(new NotFoundException("Non existent company: " + company)))
                .flatMap(providerEntity -> {
                    BeanUtils.copyProperties(provider, providerEntity);
                    return this.providerRepository.save(providerEntity);
                })
                .map(ProviderEntity::toProvider);
    }

    private Mono<Void> assertNifAndCompanyNotExist(String nif, String company) {
        return this.providerRepository.findByNif(nif)
                .mergeWith(this.providerRepository.findByCompany(company))
                .flatMap(provider ->
                        Mono.error(new ConflictException("Existing already nif or company: " + nif + ", " + company))
                )
                .then();
    }
}
