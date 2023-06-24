package br.com.fiap.tech.challenge.rest.config;

import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan("br.com.fiap.tech.challenge.rest")
public class RestConfiguration {

    @Bean("restModelMapper")
    @Autowired(required = false)
    public ModelMapper modelMapper(List<TypeMapConfiguration> configurations){
        var mapper =  new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        configurations.forEach(c -> c.configure(mapper));

        return mapper;
    }

}
