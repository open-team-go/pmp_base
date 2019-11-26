package com.arz.pmp.base.framework.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author : ljj
 * @version V1.0
 * @Project: panli-system
 * @Package com.yug.distribution.config
 * @date Date : 2019年07月18日 16:02
 */
@Configuration
public class OrikaConfig {

    @Bean
    MapperFactory mapperFactory() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        return mapperFactory;
    }

    @Bean
    MapperFacade mapperFacade() {
        MapperFacade mapper = mapperFactory().getMapperFacade();
        return mapper;
    }
}
