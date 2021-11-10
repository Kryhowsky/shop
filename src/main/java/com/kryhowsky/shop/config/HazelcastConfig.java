package com.kryhowsky.shop.config;

import com.hazelcast.config.*;
import com.kryhowsky.shop.model.dao.Product;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class HazelcastConfig {

    @Bean
    public SerializationConfig configHazelcast() {
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig()
                        .setName("products")
                        .setEvictionConfig(new EvictionConfig()
                                .setSize(100)
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE))
                        .setTimeToLiveSeconds(60 * 60 * 24))
                .getSerializationConfig()
                .addDataSerializableFactory(1, (int id) -> (id == 1) ? new Product() : null);
    }

}
