package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service;

import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.enterprise.entity.Payment;
import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    private final BeanFactory factory;

    @Override
    public Payment pay(Payment payment) {
        return current(payment.method()).pay(payment);
    }

    private PaymentGateway current(PaymentMethod method) {
        return factory.getBeanProvider(PaymentGateway.class)
                .stream()
                .filter(pq -> pq.accept(method))
                .findFirst()
                .orElseThrow();
    }
}
