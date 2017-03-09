package com.levymoreira.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.levymoreira.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.levymoreira.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.levymoreira.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.levymoreira.domain.AccountName.class.getName(), jcacheConfiguration);
            cm.createCache(com.levymoreira.domain.AccountType.class.getName(), jcacheConfiguration);
            cm.createCache(com.levymoreira.domain.Category.class.getName(), jcacheConfiguration);
            cm.createCache(com.levymoreira.domain.Client.class.getName(), jcacheConfiguration);
            cm.createCache(com.levymoreira.domain.CreditCardInvoice.class.getName(), jcacheConfiguration);
            cm.createCache(com.levymoreira.domain.InstallmentGroup.class.getName(), jcacheConfiguration);
            cm.createCache(com.levymoreira.domain.Transaction.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
