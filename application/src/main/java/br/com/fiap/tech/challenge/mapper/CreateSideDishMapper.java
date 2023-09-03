package br.com.fiap.tech.challenge.mapper;

import br.com.fiap.tech.challenge.dto.CreateSingleProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.SideDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommonMapper.class})
public interface CreateSideDishMapper {

    CreateSideDishMapper INSTANCE = Mappers.getMapper(CreateSideDishMapper.class);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    SideDish toSideDish(CreateSingleProductDTO request);
}
