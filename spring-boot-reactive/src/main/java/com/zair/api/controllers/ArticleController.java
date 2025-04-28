package com.zair.api.controllers;

import com.zair.api.dtos.Article;
import com.zair.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ArticleController.ARTICLES)
@RequiredArgsConstructor
public class ArticleController {
    public static final String ARTICLES = "/articles";
    public static final String ID_BARCODE = "/{barcode}";

    private final ArticleService articleService;

    @PostMapping
    public Mono<Article> create(@RequestBody Article article) {
        article.doDefault();
        return this.articleService.create(article);
    }

    @GetMapping(ID_BARCODE)
    public Mono<Article> read(@PathVariable String barcode) {
        return this.articleService.read(barcode);
    }

    @PutMapping(ID_BARCODE)
    public Mono<Article> update(@PathVariable String barcode, @RequestBody Article article) {
        article.doDefault();
        return this.articleService.update(barcode, article);
    }
}
