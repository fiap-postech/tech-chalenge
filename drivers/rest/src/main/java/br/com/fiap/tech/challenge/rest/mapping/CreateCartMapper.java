package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.dto.CreateCartDTO;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CreateCartMapper {

    CreateCartDTO toDTO(CreateCartRequest request);

}
