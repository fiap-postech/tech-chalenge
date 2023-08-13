package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PaymentEntity;
import br.com.fiap.tech.challenge.domain.entity.Payment;
import br.com.fiap.tech.challenge.domain.enums.PaymentMethod;
import org.javamoney.moneta.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaymentMapper {

    @Mapping(target = "amount", source = "amount", qualifiedByName = "getAmount")
    @Mapping(target = "method", source = "method", qualifiedByName = "getPayment")
    @Mapping(target = "uuid", source = "uuid", qualifiedByName = "generateUuid")
    Payment toPayment(PaymentEntity payment);


    @Mapping(target = "amount", expression = "java(Mappings.moneyToBigDecimalConverter(payment.amount()))")
    @Mapping(target = "method", constant = "MERCADO_PAGO")
    PaymentEntity toPaymentEntity(Payment payment);


    @Named("getAmount")
    static Money getAmount (BigDecimal amount){
        return makeMoney(amount);
    }



    @Named("getPayment")
    static PaymentMethod getPayment(String method) {
        var teste  = "teste";
        if ("MERCADO_PAGO".equals(method)) {
            return PaymentMethod.PAID_MARKET;
        }

        throw new IllegalArgumentException();
    }

    @Named("generateUuid")
    static UUID generateUuid(String uuid){
        return UUID.fromString(uuid);
    }
}
