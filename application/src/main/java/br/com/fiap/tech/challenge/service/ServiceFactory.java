package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.port.driven.*;
import br.com.fiap.tech.challenge.port.driver.*;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static FindAllAvailableProductService findAllAvailableProductService(ProductReaderService reader) {
        return new FindAllAvailableProductServiceImpl(reader);
    }

    public static FindAllAvailableProductByCategory findAllAvailableProductByCategory(ProductReaderService reader) {
        return new FindAllAvailableProductByCategoryImpl(reader);
    }

    public static FindProductByUUIDService findProductByUUIDService(ProductReaderService reader) {
        return new FindProductByUUIDServiceImpl(reader);
    }

    public static CreateProductService createProductService(ProductWriterService writer) {
        return new CreateProductServiceImpl(writer);
    }

    public static UpdateProductService updateProductService(ProductReaderService reader, ProductWriterService writer) {
        return new UpdateProductServiceImpl(reader, writer);
    }

    public static UpdatePurchaseService updatePurchaseService(PurchaseWriterService writer) {
        return new UpdatePurchaseServiceImpl(writer);
    }

    public static EnableProductService enableProductService(ProductWriterService writer) {
        return new EnableProductServiceImpl(writer);
    }

    public static DisableProductService disableProductService(ProductWriterService writer) {
        return new DisableProductServiceImpl(writer);
    }

    public static CreateCustomerService createCustomerService(CustomerWriterService writer, CustomerReaderService reader) {
        return new CreateCustomerServiceImpl(writer, reader);
    }

    public static FindCustomerByDocumentService findCustomerByDocumentService(CustomerReaderService reader) {
        return new FindCustomerByDocumentServiceImpl(reader);
    }

    public static FindCustomerByUUIDService findFindCustomerByUUIDService(CustomerReaderService reader) {
        return new FindCustomerByUUIDServiceImpl(reader);
    }

    public static UpgradeCustomerService upgradeCustomerService(CustomerWriterService writer) {
        return new UpgradeCustomerServiceImpl(writer);
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