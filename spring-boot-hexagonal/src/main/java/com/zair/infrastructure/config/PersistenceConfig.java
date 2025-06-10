package com.zair.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.zair.infrastructure.adapter.out.persistence")
public class PersistenceConfig {
}
