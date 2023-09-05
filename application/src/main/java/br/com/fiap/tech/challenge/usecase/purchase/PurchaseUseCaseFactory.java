package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.gateway.PaymentGateway;
import br.com.fiap.tech.challenge.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.usecase.cart.FindCartByUUIDUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseUseCaseFactory {

    public static CheckoutUseCase checkoutUseCase(FindCartByUUIDUseCase cartFinderUseCase, CreatePurchaseUseCase createPurchaseUseCase, PaymentGateway paymentGateway) {
        return new CheckoutUseCaseImpl(cartFinderUseCase, createPurchaseUseCase, paymentGateway);
    }

    public static CreatePurchaseUseCase createPurchaseUseCase(PurchaseWriterGateway writer) {
        return new CreatePurchaseUseCaseImpl(writer);
    }

    public static UpdatePurchaseStatusUseCase updatePurchaseUseCase(FindPurchaseByUUIDUseCase findPurchaseUseCase, PurchaseWriterGateway gateway) {
        return new UpdatePurchaseStatusUseCaseImpl(findPurchaseUseCase, gateway);
    }

    public static FindAllPurchasesUseCase findAllPurchasesUseCase(PurchaseReaderGateway gateway) {
        return new FindAllPurchasesUseCaseImpl(gateway);
    }

    public static FindPurchaseByPaymentIdUseCase findPurchaseByPaymentIdUseCase(PaymentGateway paymentGateway, PurchaseReaderGateway purchaseReaderGateway) {
        return new FindPurchaseByPaymentIdUseCaseImpl(paymentGateway, purchaseReaderGateway);
    }

    public static FindPurchaseByUUIDUseCase findPurchaseByUUIDUseCase(PurchaseReaderGateway gateway) {
        return new FindPurchaseByUUIDUseCaseImpl(gateway);
    }

    public static PaymentConfirmUseCase paymentConfirmUseCase(FindPurchaseByPaymentIdUseCase findPurchaseUseCase, UpdatePurchaseStatusUseCase updatePurchaseStatusUseCase) {
        return new PaymentConfirmUseCaseImpl(findPurchaseUseCase, updatePurchaseStatusUseCase);
    }

}
