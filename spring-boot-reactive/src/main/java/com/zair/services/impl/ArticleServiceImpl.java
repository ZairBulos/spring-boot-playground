package com.zair.services.impl;

import com.zair.api.dtos.Article;
import com.zair.data.entities.ArticleEntity;
import com.zair.data.repositories.ArticleRepository;
import com.zair.data.repositories.ProviderRepository;
import com.zair.services.ArticleService;
import com.zair.services.exceptions.ConflictException;
import com.zair.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ProviderRepository providerRepository;

    @Override
    public Mono<Article> read(String barcode) {
        return this.articleRepository
                .findByBarcode(barcode)
                .switchIfEmpty(Mono.error(new NotFoundException("Non existent barcode: " + barcode)))
                .map(ArticleEntity::toArticle);
    }

    @Override
    public Mono<Article> create(Article article) {
        return this
                .assertBarcodeNotExist(article.getBarcode())
                .then(Mono.justOrEmpty(article.getProviderCompany()))
                .flatMap(providerCompany ->
                        this.providerRepository
                                .findByCompany(article.getProviderCompany())
                                .switchIfEmpty(Mono.error(new NotFoundException("Non existent provider: " + article.getProviderCompany())))
                )
                .map(providerEntity -> new ArticleEntity(article, providerEntity))
                .switchIfEmpty(Mono.just(new ArticleEntity(article, null)))
                .flatMap(this.articleRepository::save)
                .map(ArticleEntity::toArticle);
    }

    @Override
    public Mono<Article> update(String barcode, Article article) {
        return this
                .articleRepository.findByBarcode(barcode)
                .switchIfEmpty(Mono.error(new NotFoundException("Non existent barcode:" + barcode)))
                .flatMap(articleEntity -> {
                    BeanUtils.copyProperties(article, articleEntity);

                    return this.providerRepository
                            .findByCompany(article.getProviderCompany())
                            .switchIfEmpty(Mono.error(new NotFoundException("Non existent provider: " + article.getProviderCompany())))
                            .map(providerEntity -> {
                                articleEntity.setProviderEntity(providerEntity);
                                return articleEntity;
                            });
                })
                .flatMap(this.articleRepository::save)
                .map(ArticleEntity::toArticle);
    }

    private Mono<Void> assertBarcodeNotExist(String barcode) {
        return this.articleRepository
                .findByBarcode(barcode)
                .flatMap(articleEntity -> Mono.error(new ConflictException("Barcode already exists: " + barcode)))
                .then();
    }
}
