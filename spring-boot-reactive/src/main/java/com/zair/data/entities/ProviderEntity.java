package com.zair.data.entities;

import com.zair.api.dtos.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ProviderEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nif;

    @Indexed(unique = true)
    private String company;

    public ProviderEntity(Provider provider) {
        BeanUtils.copyProperties(provider, this);
    }

    public Provider toProvider() {
        Provider provider = new Provider();
        BeanUtils.copyProperties(this, provider);
        return provider;
    }
}
