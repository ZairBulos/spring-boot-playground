package com.zair.api;

import com.zair.api.controllers.ArticleController;
import com.zair.api.controllers.ProviderController;
import com.zair.api.dtos.Article;
import com.zair.api.dtos.Provider;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ApiTestConfig
class ArticleControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void testCreate() {
        Provider provider = Provider.builder().nif("tan1").company("tac1").build();
        this.webTestClient.post()
                .uri(ProviderController.PROVIDERS)
                .body(Mono.just(provider), Provider.class)
                .exchange()
                .expectStatus().isOk();

        Article article = Article.builder().barcode("ta0001").description("tad1").providerCompany("tac1").build();
        this.webTestClient
                .post()
                .uri(ArticleController.ARTICLES)
                .body(Mono.just(article), Article.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Article.class)
                .value(articleData -> {
                    assertEquals("ta0001", articleData.getBarcode());
                    assertEquals("tad1", articleData.getDescription());
                    assertEquals("tac1", articleData.getProviderCompany());
                    assertEquals(BigDecimal.ZERO, articleData.getRetailPrice());
                    assertEquals(10, articleData.getStock());
                    assertFalse(articleData.getDiscontinued());
                });
    }

    @Test
    @Order(2)
    void testCreateConflictBarcodeExists() {
        Article article = Article.builder().barcode("ta0002").description("tad2").build();
        this.webTestClient
                .post()
                .uri(ArticleController.ARTICLES)
                .body(Mono.just(article), Article.class)
                .exchange()
                .expectStatus().isOk();

        Article articleConflict = Article.builder().barcode("ta0002").description("tad2").build();
        this.webTestClient
                .post()
                .uri(ArticleController.ARTICLES)
                .body(Mono.just(articleConflict), Article.class)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @Order(3)
    void testCreateConflictNotFoundProvider() {
        Article article = Article.builder().barcode("ta0003").description("tad3").providerCompany("nn").build();
        this.webTestClient
                .post()
                .uri(ArticleController.ARTICLES)
                .body(Mono.just(article), Article.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(4)
    void testRead() {
        Article article = Article.builder().barcode("ta0004").description("tad4").build();
        this.webTestClient
                .post()
                .uri(ArticleController.ARTICLES)
                .body(Mono.just(article), Article.class)
                .exchange()
                .expectStatus().isOk();

        this.webTestClient
                .get()
                .uri(ArticleController.ARTICLES + ArticleController.ID_BARCODE, "ta0004")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Article.class);
    }

    @Test
    @Order(5)
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(ArticleController.ARTICLES + ArticleController.ID_BARCODE, "nn")
                .exchange()
                .expectStatus().isNotFound();
    }
}
