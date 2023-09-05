package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.Payment;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentStatus;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.application.gateway.PaymentGateway;
import br.com.fiap.tech.challenge.application.usecase.cart.FindCartByUUIDUseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.PAYMENT_ERROR;

@RequiredArgsConstructor
class CheckoutUseCaseImpl implements CheckoutUseCase {

    private final FindCartByUUIDUseCase cartFinderUseCase;
    private final CreatePurchaseUseCase createPurchaseUseCase;
    private final PaymentGateway paymentGateway;

    @Override
    public Purchase checkout(UUID cartId) {
        var cart = cartFinderUseCase.get(cartId);
        var payment = createPayment(cart);
        var purchase = Purchase.newPurchase(cart, payment);

        var urlPayment = doPayment(purchase);
        var purchaseResult = createPurchaseUseCase.create(purchase);

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
