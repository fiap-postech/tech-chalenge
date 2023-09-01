package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Payment;
import br.com.fiap.tech.challenge.rest.resource.response.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Mappings.moneyToBigDecimalConverter;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaymentMapperRest {

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "date", expression = "java(source.date())")
    @Mapping(target = "status", expression = "java(source.status())")
    @Mapping(target = "method", expression = "java(source.method())")
    @Mapping(target = "amount", source = "source", qualifiedByName = "getAmount")
    PaymentResponse toPaymentResponse(Payment source);

    @Named("getAmount")
    static BigDecimal getAmount(Payment source) {
        return moneyToBigDecimalConverter(source.amount());
    }

}
