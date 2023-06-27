package br.com.fiap.tech.challenge.adapter.driven.mysql.config;

import br.com.fiap.tech.challenge.mapper.common.ModelMapperConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MySQLModelMapperConfiguration implements ModelMapperConfiguration<MySQLTypeMapConfiguration> {

    public static final String MYSQL_MODEL_MAPPER = "MYSQL_MODEL_MAPPER";

    @Bean(MYSQL_MODEL_MAPPER)
    @Autowired(required = false)
    public ModelMapper modelMapper(List<MySQLTypeMapConfiguration> configurations){
        return getConfiguredModelMapper(configurations);
    }

}
