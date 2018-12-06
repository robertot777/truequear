package truequear.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(truequear.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(truequear.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(truequear.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(truequear.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(truequear.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(truequear.domain.Comuna.class.getName(), jcacheConfiguration);
            cm.createCache(truequear.domain.Region.class.getName(), jcacheConfiguration);
            cm.createCache(truequear.domain.Direccion.class.getName(), jcacheConfiguration);
            cm.createCache(truequear.domain.Cliente.class.getName(), jcacheConfiguration);
            cm.createCache(truequear.domain.Valoracion.class.getName(), jcacheConfiguration);
            cm.createCache(truequear.domain.Objetos.class.getName(), jcacheConfiguration);
            cm.createCache(truequear.domain.Oferta.class.getName(), jcacheConfiguration);
            cm.createCache(truequear.domain.RespuestaOferta.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
