package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.port.driver.CreateCustomerService;
import br.com.fiap.tech.challenge.port.driver.CreateProductService;
import br.com.fiap.tech.challenge.port.driver.DisableProductService;
import br.com.fiap.tech.challenge.port.driver.EnableProductService;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductByCategory;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductService;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByDocumentService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
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

    public static UpgradeCustomerService upgradeCustomerService(CustomerWriterService writer){
        return new UpgradeCustomerServiceImpl(writer);
    }

    public static CheckoutService checkoutService(PaymentGatewayService paymentGateway) {
        return new CheckoutServiceImpl(paymentGateway);
    }

}
