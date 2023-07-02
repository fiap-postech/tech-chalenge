package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service;

import br.com.fiap.tech.challenge.domain.PaymentMethod;
import br.com.fiap.tech.challenge.domain.entity.Payment;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PaidMarket implements PaymentGateway{

    @Override
    public boolean accept(PaymentMethod method) {
        return PaymentMethod.PAID_MARKET.equals(method);
    }

    @Override
    @SuppressWarnings("squid:S112")
    public Payment pay(Payment payment) {
        try {
            var random = ThreadLocalRandom.current();

            var time = random.nextInt(1,5);
            Thread.sleep(Duration.ofSeconds(time).toMillis());

            if (random.nextBoolean()){
                return payment.paid();
            } else {
                return payment.error();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
