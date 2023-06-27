package br.com.fiap.tech.challenge.rest.config;

import br.com.fiap.tech.challenge.mapper.common.ModelMapperConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RestModelMapperConfiguration implements ModelMapperConfiguration<RestTypeMapConfiguration> {

    public static final String REST_MODEL_MAPPER = "REST_MODEL_MAPPER";

    @Bean(REST_MODEL_MAPPER)
    @Autowired(required = false)
    public ModelMapper modelMapper(List<RestTypeMapConfiguration> configurations){
        return getConfiguredModelMapper(configurations);
    }
}
