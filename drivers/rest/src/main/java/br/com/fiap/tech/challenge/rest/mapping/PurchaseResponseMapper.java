package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.rest.resource.response.PurchseResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = { PurchaseItemResponseMapper.class, CustomerResponseMapper.class, PaymentResponseMapper.class }
)
public interface PurchaseResponseMapper {

    PurchseResponse toResponse(PurchaseDTO dto);
}
