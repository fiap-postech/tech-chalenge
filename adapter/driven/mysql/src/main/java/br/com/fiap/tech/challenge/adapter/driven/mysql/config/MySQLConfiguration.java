package br.com.fiap.tech.challenge.adapter.driven.mysql.config;

import br.com.fiap.tech.challenge.mapper.common.ModelMapperConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories("br.com.fiap.tech.challenge.adapter.driven.mysql.repository")
@EntityScan("br.com.fiap.tech.challenge.adapter.driven.mysql.model")
@ComponentScan("br.com.fiap.tech.challenge.adapter.driven.mysql")
public class MySQLConfiguration implements ModelMapperConfiguration<MySQLTypeMapConfiguration> {

    @Bean("mysqlModelMapper")
    @Autowired(required = false)
    public ModelMapper modelMapper(List<MySQLTypeMapConfiguration> configurations){
        return getConfiguredMapper(configurations);
    }

}

