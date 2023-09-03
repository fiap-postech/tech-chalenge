package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.dto.CreateCustomerDTO;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CreateCustomerMapper {

    CreateCustomerDTO toDTO(CreateCustomerRequest request);

}
