package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Payment;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Mapper
@RequiredArgsConstructor
public class PaymentToPaymentResponseMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Payment.class, PaymentResponse.class)
                .addMapping(Payment::uuid, PaymentResponse::setId)
                .addMapping(Payment::status, PaymentResponse::setStatus)
                .addMapping(Payment::date, PaymentResponse::setDate)
                .addMapping(Payment::method, PaymentResponse::setMethod);
    }
}
