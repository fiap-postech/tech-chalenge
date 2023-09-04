package br.com.fiap.tech.challenge.service;


import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import br.com.fiap.tech.challenge.port.driven.PurchaseReaderService;
import br.com.fiap.tech.challenge.port.driven.PurchaseWriterService;
import br.com.fiap.tech.challenge.port.driver.AddCartItemService;
import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.port.driver.CreateCartService;
import br.com.fiap.tech.challenge.port.driver.CreatePurchaseService;
import br.com.fiap.tech.challenge.port.driver.FindAllPurchasesService;
import br.com.fiap.tech.challenge.port.driver.FindCartByUUIDService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByUUIDService;
import br.com.fiap.tech.challenge.port.driver.RemoveCartItemService;
import br.com.fiap.tech.challenge.port.driver.UpdateCartItemService;
import br.com.fiap.tech.challenge.port.driver.UpdatePurchaseService;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static FindCartByUUIDService findCartByUUIDService(CartReaderService reader) {
        return new FindCartByUUIDServiceImpl(reader);
    }

    public static CreateCartService createCartService(CartWriterService writer) {
        return new CreateCartServiceImpl(writer);
    }

    public static AddCartItemService addCartItemService(CartReaderService reader, CartWriterService writer) {
        return new AddCartItemServiceImpl(reader, writer);
    }

    public static UpdateCartItemService updateCartItemService(CartReaderService reader, CartWriterService writer) {
        return new UpdateCartItemServiceImpl(reader, writer);
    }

    public static RemoveCartItemService removeCartItemService(CartReaderService reader, CartWriterService writer) {
        return new RemoveCartItemServiceImpl(reader, writer);
    }

    public static CheckoutService checkoutService(FindCartByUUIDService findCartService,
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

    public static FindPurchaseByPaymentIdService findPurchaseByPaymentIdService(PaymentGatewayService paymentGateway,
                                                                                PurchaseReaderService readerService) {
        return new FindPurchaseByPaymentIdServiceImpl(paymentGateway, readerService);
    }
}