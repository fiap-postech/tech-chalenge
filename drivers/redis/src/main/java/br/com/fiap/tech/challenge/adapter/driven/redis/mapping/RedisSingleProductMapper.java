package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RedisSingleProductMapper {

    ProductEntity toEntity(ProductDTO dto);

    ProductDTO toDTO(ProductEntity entity);

}
