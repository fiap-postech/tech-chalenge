package br.com.fiap.tech.challenge.adapter.driven.redis.config;

import br.com.fiap.tech.challenge.mapper.common.ModelMapperConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RedisModelMapperConfiguration implements ModelMapperConfiguration<RedisTypeMapConfiguration> {

    public static final String REDIS_MODEL_MAPPER = "REDIS_MODEL_MAPPER";

    @Bean(REDIS_MODEL_MAPPER)
    @Autowired(required = false)
    public ModelMapper modelMapper(List<RedisTypeMapConfiguration> configurations){
        return getConfiguredModelMapper(configurations);
    }

}
