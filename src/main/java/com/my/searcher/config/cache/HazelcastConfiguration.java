package com.my.searcher.config.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.my.searcher.constants.Constants;

@Configuration
public class HazelcastConfiguration {

 @Bean
    public Config hazelCastConfig(){
        return new Config()
                .setInstanceName(Constants.HAZLECAST_INSTANCE)
                .addMapConfig(
                        new MapConfig()
                                .setName(Constants.CACHE_NAME)
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(86400));
    }

}