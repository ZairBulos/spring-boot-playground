package com.zair.api.controllers;

import com.zair.api.dtos.Provider;
import com.zair.services.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ProviderController.PROVIDERS)
@RequiredArgsConstructor
public class ProviderController {
    public static final String PROVIDERS = "/providers";
    public static final String ID_COMPANY = "/{company}";

    private final ProviderService providerService;

    @PostMapping
    public Mono<Provider> create(@RequestBody Provider provider) {
        return this.providerService.create(provider);
    }

    @GetMapping(ID_COMPANY)
    public Mono<Provider> read(@PathVariable String company) {
        return this.providerService.read(company);
    }

    @PutMapping(ID_COMPANY)
    public Mono<Provider> update(@PathVariable String company, @RequestBody Provider provider) {
        return this.providerService.update(company, provider);
    }
}
