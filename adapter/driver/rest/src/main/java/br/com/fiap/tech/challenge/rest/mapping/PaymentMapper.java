package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Payment;
import br.com.fiap.tech.challenge.rest.resource.response.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaymentMapper {

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    PaymentResponse toPaymentResponse(Payment source);

}
