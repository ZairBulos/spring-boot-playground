package com.zair.services;

import com.zair.api.dtos.Article;
import reactor.core.publisher.Mono;

public interface ArticleService {
    public Mono<Article> read(String barcode);
    public Mono<Article> create(Article article);
    public Mono<Article> update(String barcode, Article article);
}
