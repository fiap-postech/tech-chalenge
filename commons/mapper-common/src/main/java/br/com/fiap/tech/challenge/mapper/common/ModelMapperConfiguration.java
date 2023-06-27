package br.com.fiap.tech.challenge.mapper.common;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;

public interface ModelMapperConfiguration<T extends TypeMapConfiguration> {
    default ModelMapper getConfiguredModelMapper(List<T> configurations){
        var mapper =  new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        configurations.forEach(c -> c.configure(mapper));

        return mapper;
    }
}
