package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driven.PurchaseWriterService;
import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.port.driver.AddCartItemService;
import br.com.fiap.tech.challenge.port.driver.CreateCartService;
import br.com.fiap.tech.challenge.port.driver.CreateCustomerService;
import br.com.fiap.tech.challenge.port.driver.CreateProductService;
import br.com.fiap.tech.challenge.port.driver.CreatePurchaseService;
import br.com.fiap.tech.challenge.port.driver.DisableProductService;
import br.com.fiap.tech.challenge.port.driver.EnableProductService;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductByCategory;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductService;
import br.com.fiap.tech.challenge.port.driver.FindCartByUUIDService;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByDocumentService;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByUUIDService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.port.driver.RemoveCartItemService;
import br.com.fiap.tech.challenge.port.driver.UpdateCartItemService;
import br.com.fiap.tech.challenge.port.driver.UpdateProductService;
import br.com.fiap.tech.challenge.port.driver.UpgradeCustomerService;

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

    public static EnableProductService enableProductService(ProductWriterService writer) {
        return new EnableProductServiceImpl(writer);
    }

    public static DisableProductService disableProductService(ProductWriterService writer) {
        return new DisableProductServiceImpl(writer);
    }
    public static CreateCustomerService createCustomerService(CustomerWriterService writer, CustomerReaderService reader){
        return new CreateCustomerServiceImpl(writer, reader);
    }

    public static FindCustomerByDocumentService findCustomerByDocumentService(CustomerReaderService reader){
        return new FindCustomerByDocumentServiceImpl(reader);
    }

    public static FindCustomerByUUIDService findFindCustomerByUUIDService(CustomerReaderService reader){
        return new FindCustomerByUUIDServiceImpl(reader);
    }

    public static UpgradeCustomerService upgradeCustomerService(CustomerWriterService writer){
        return new UpgradeCustomerServiceImpl(writer);
    }

    public static FindCartByUUIDService findCartByUUIDService(CartReaderService reader) {
        return new FindCartByUUIDServiceImpl(reader);
    }

    public static CreateCartService createCartService(CartWriterService writer) {
        return new CreateCartServiceImpl(writer);
    }

    public  static AddCartItemService addCartItemService(CartReaderService reader, CartWriterService writer) {
        return new AddCartItemServiceImpl(reader, writer);
    }

    public  static UpdateCartItemService updateCartItemService(CartReaderService reader, CartWriterService writer) {
        return new UpdateCartItemServiceImpl(reader, writer);
    }

    public  static RemoveCartItemService removeCartItemService(CartReaderService reader, CartWriterService writer) {
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
}