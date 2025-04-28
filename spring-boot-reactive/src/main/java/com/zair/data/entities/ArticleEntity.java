package com.zair.data.entities;

import com.zair.api.dtos.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ArticleEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String barcode;

    private String description;

    private BigDecimal retailPrice;

    private Integer stock;

    private Boolean discontinued;

    @DBRef
    private ProviderEntity providerEntity;

    public ArticleEntity(Article article) {
        BeanUtils.copyProperties(article, this);
    }

    public ArticleEntity(Article article, ProviderEntity providerEntity) {
        BeanUtils.copyProperties(article, this);
        this.providerEntity = providerEntity;
    }

    public Article toArticle() {
        Article article = new Article();
        BeanUtils.copyProperties(this, article);
        if (Objects.nonNull(this.getProviderEntity())) {
            article.setProviderCompany(this.getProviderEntity().getCompany());
        }
        return article;
    }
}
