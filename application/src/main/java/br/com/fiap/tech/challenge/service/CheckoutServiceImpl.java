package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.PaymentMethod;
import br.com.fiap.tech.challenge.domain.PaymentStatus;
import br.com.fiap.tech.challenge.domain.Purchase;
import br.com.fiap.tech.challenge.domain.entity.Payment;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.port.driver.CreatePurchaseService;
import br.com.fiap.tech.challenge.port.driver.FindCartByUUIDService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import static br.com.fiap.tech.challenge.error.ApplicationError.PAYMENT_ERROR;

@RequiredArgsConstructor
class CheckoutServiceImpl implements CheckoutService {

    private final FindCartByUUIDService cartFinder;
    private final CreatePurchaseService purchaseService;
    private final PaymentGatewayService paymentGateway;

    @Override
    public Purchase checkout(UUID cartId) {
        var cart = cartFinder.get(cartId);

        var payment = doPayment(cart);

        return purchaseService.create(Purchase.newPurchase(cart, payment));
    }

    private Payment doPayment(Cart cart) {
        var payment = paymentGateway.pay(createPayment(cart));

        if (payment.isError()) {
            throw new ApplicationException(PAYMENT_ERROR);
        }

        return payment;
    }

    private Payment createPayment(Cart cart) {
        return Payment.builder()
                .status(PaymentStatus.CREATED)
                .date(LocalDate.now())
                .method(PaymentMethod.PAID_MARKET)
                .amount(cart.total().amount())
                .build();
    }
}
