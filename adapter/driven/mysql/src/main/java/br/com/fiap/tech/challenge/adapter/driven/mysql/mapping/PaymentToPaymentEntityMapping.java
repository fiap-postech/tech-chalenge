package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PaymentEntity;
import br.com.fiap.tech.challenge.domain.PaymentMethod;
import br.com.fiap.tech.challenge.domain.entity.Payment;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.moneyToBigDecimalConverter;
import static java.util.Objects.isNull;

@Mapper
@RequiredArgsConstructor
public class PaymentToPaymentEntityMapping implements MySQLTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Payment.class, PaymentEntity.class)
                .addMapping(Payment::uuid, PaymentEntity::setUuid)
                .addMapping(Payment::status, PaymentEntity::setStatus)
                .addMapping(Payment::date, PaymentEntity::setDate)
                .addMappings(mapping -> mapping.using(paymentMethodStringConverter()).map(Payment::method, PaymentEntity::setMethod))
                .addMappings(mapping -> mapping.using(moneyToBigDecimalConverter()).map(Payment::amount, PaymentEntity::setAmount));
    }

    private Converter<PaymentMethod, String> paymentMethodStringConverter() {
        return ctx -> {
            var source = ctx.getSource();

            if (isNull(source)) {
                return null;
            }

            return switch (source){
                case PAID_MARKET -> "MERCADO_PAGO";
            };
        };
    }
}
