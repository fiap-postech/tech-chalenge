package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.Payment;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentStatus;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.port.driver.CreatePurchaseService;
import br.com.fiap.tech.challenge.port.driver.FindCartByUUIDService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.PAYMENT_ERROR;

@RequiredArgsConstructor
class CheckoutServiceImpl implements CheckoutService {

    private final FindCartByUUIDService cartFinder;
    private final CreatePurchaseService purchaseService;
    private final PaymentGatewayService paymentGateway;

    @Override
    public Purchase checkout(UUID cartId) {
        var cart = cartFinder.get(cartId);
        var payment = createPayment(cart);
        var purchase = Purchase.newPurchase(cart, payment);

        var urlPayment = doPayment(purchase);
        var purchaseResult = purchaseService.create(purchase);

        purchaseResult.payment().setUrlPayment(urlPayment);
        return purchaseResult;
    }

    private String doPayment(Purchase purchase) {
        var urlPaymentOpt = paymentGateway.pay(purchase);

        if (urlPaymentOpt.isEmpty()) {
            throw new ApplicationException(PAYMENT_ERROR);
        }
        return urlPaymentOpt.get();
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
