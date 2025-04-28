package com.zair.api;

import com.zair.api.controllers.ProviderController;
import com.zair.api.dtos.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ApiTestConfig
class ProviderControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void testCreate() {
        Provider provider = Provider.builder().nif("tpn1").company("tpc1").build();

        this.webTestClient
                .post()
                .uri(ProviderController.PROVIDERS)
                .body(Mono.just(provider), Provider.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Provider.class)
                .value(Assertions::assertNotNull)
                .value(providerData -> {
                    assertEquals("tpn1", providerData.getNif());
                    assertEquals("tpc1", providerData.getCompany());
                });
    }

    @Test
    @Order(2)
    void testCreateConflict() {
        Provider provider = Provider.builder().nif("tpn1").company("tpc2").build();

        this.webTestClient
                .post()
                .uri(ProviderController.PROVIDERS)
                .body(Mono.just(provider), Provider.class)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @Order(3)
    void testRead() {
        this.webTestClient
                .get()
                .uri(ProviderController.PROVIDERS + ProviderController.ID_COMPANY, "tpc1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(4)
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(ProviderController.PROVIDERS + ProviderController.ID_COMPANY, "nn")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(5)
    void testUpdate() {
        Provider provider = Provider.builder().nif("tpn5").company("tpc5").build();

        this.webTestClient
                .put()
                .uri(ProviderController.PROVIDERS + ProviderController.ID_COMPANY, "tpc1")
                .body(Mono.just(provider), Provider.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Provider.class)
                .value(Assertions::assertNotNull)
                .value(providerData -> {
                    assertEquals("tpn5", providerData.getNif());
                    assertEquals("tpc5", providerData.getCompany());
                });
    }
}
