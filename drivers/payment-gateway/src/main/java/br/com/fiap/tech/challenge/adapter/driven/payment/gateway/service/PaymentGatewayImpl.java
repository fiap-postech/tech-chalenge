package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

    private final BeanFactory factory;

    @Override
    public Optional<String> pay(Purchase purchase) {
        var payment = purchase.payment();
        return current(payment.method()).pay(purchase);
    }

    @Override
    public Optional<String> getPurchaseUUID(String paymentId) {
        return current(PaymentMethod.PAID_MARKET).getPurchaseUUID(paymentId);
    }

    private br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service.PaymentGateway current(PaymentMethod method) {
        return factory.getBeanProvider(br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service.PaymentGateway.class)
                .stream()
                .filter(pq -> pq.accept(method))
                .findFirst()
                .orElseThrow();
    }


}
