package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service;

import br.com.fiap.tech.challenge.domain.enums.PaymentMethod;
import br.com.fiap.tech.challenge.domain.entity.Payment;
import io.vavr.Lazy;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Service
public class PaidMarket implements PaymentGateway{

    private final Lazy<SplittableRandom> random = Lazy.of(SplittableRandom::new);

    @Override
    public boolean accept(PaymentMethod method) {
        return PaymentMethod.PAID_MARKET.equals(method);
    }

    @Override
    @SuppressWarnings("squid:S112")
    public Payment pay(Payment payment) {
        try {
            var currentRandom = ThreadLocalRandom.current();

            var time = currentRandom.nextInt(1,5);
            Thread.sleep(Duration.ofSeconds(time).toMillis());

            return withProbability(
                    payment::paid,
                    payment::error,
                    95
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private <T> T withProbability(Supplier<T> positiveCase, Supplier<T> negativeCase, int probability) {
        SplittableRandom current = this.random.get();

        if (current.nextInt(1, 101) <= probability) {
            return positiveCase.get();
        } else {
            return negativeCase.get();
        }
    }
}
