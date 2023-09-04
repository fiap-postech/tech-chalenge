package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ComboProductEntity;
import br.com.fiap.tech.challenge.adapter.dto.ComboDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { RedisSingleProductMapper.class })
public interface RedisComboProductMapper {

    ComboProductEntity toEntity(ComboDTO dto);

    ComboDTO toDTO(ComboProductEntity entity);
}
