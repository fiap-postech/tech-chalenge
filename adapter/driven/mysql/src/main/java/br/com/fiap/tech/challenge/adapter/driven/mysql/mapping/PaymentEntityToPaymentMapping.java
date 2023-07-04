package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PaymentEntity;
import br.com.fiap.tech.challenge.domain.enums.PaymentMethod;
import br.com.fiap.tech.challenge.domain.entity.Payment;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
@RequiredArgsConstructor
public class PaymentEntityToPaymentMapping implements MySQLTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(PaymentEntity.class, Payment.class)
                .setProvider(paymentProvider());
    }

    private Provider<Payment> paymentProvider() {
        return provision -> {
            var request = (PaymentEntity) provision.getSource();

            return Payment.builder()
                    .amount(makeMoney(request.getAmount()))
                    .method(getPayment(request.getMethod()))
                    .date(request.getDate())
                    .status(request.getStatus())
                    .uuid(UUID.fromString(request.getUuid()))
                    .build();
        };
    }

    private PaymentMethod getPayment(String  method) {
        if ("MERCADO_PAGO".equals(method)) {
            return PaymentMethod.PAID_MARKET;
        }

        throw new IllegalArgumentException();
    }
}
