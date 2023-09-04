package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import br.com.fiap.tech.challenge.port.driven.PurchaseReaderService;
import br.com.fiap.tech.challenge.port.driven.PurchaseWriterService;
import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.port.driver.CreatePurchaseService;
import br.com.fiap.tech.challenge.port.driver.FindAllPurchasesService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByUUIDService;
import br.com.fiap.tech.challenge.port.driver.UpdatePurchaseService;
import br.com.fiap.tech.challenge.usecase.cart.FindCartByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.cart.RemoveCartItemUseCase;
import br.com.fiap.tech.challenge.usecase.cart.RemoveCartItemUseCaseImpl;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static RemoveCartItemUseCase removeCartItemService(CartReaderService reader, CartWriterService writer) {
        return new RemoveCartItemUseCaseImpl(reader, writer);
    }

    public static CheckoutService checkoutService(FindCartByUUIDUseCase findCartService,
                                                  CreatePurchaseService purchaseService,
                                                  PaymentGatewayService paymentGateway) {
        return new CheckoutServiceImpl(findCartService, purchaseService, paymentGateway);
    }

    public static UpdatePurchaseService updatePurchaseService(PurchaseWriterService writer) {
        return new UpdatePurchaseServiceImpl(writer);
    }

    public static CreatePurchaseService createPurchaseService(PurchaseWriterService writer) {
        return new CreatePurchaseServiceImpl(writer);
    }

    public static FindAllPurchasesService findAllPurchasesService(PurchaseReaderService reader) {
        return new FindAllPurchasesServiceImpl(reader);
    }

    public static FindPurchaseByUUIDService findPurchaseByUUIDService(PurchaseReaderService reader) {
        return new FindPurchaseByUUIDServiceImpl(reader);
    }
}