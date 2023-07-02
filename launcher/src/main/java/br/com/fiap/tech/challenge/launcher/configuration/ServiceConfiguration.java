package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.port.driver.AddCartItemService;
import br.com.fiap.tech.challenge.port.driver.CreateCartService;
import br.com.fiap.tech.challenge.port.driver.CreateCustomerService;
import br.com.fiap.tech.challenge.port.driver.CreateProductService;
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
import br.com.fiap.tech.challenge.service.ServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public FindAllAvailableProductService findAllAvailableProductService(ProductReaderService reader) {
        return ServiceFactory.findAllAvailableProductService(reader);
    }

    @Bean
    public FindAllAvailableProductByCategory findAllAvailableProductByCategory(ProductReaderService reader) {
        return ServiceFactory.findAllAvailableProductByCategory(reader);
    }

    @Bean
    public FindProductByUUIDService findProductByUUIDService(ProductReaderService reader) {
        return ServiceFactory.findProductByUUIDService(reader);
    }

    @Bean
    public CreateProductService createProductService(ProductWriterService writer) {
        return ServiceFactory.createProductService(writer);
    }

    @Bean
    public UpdateProductService updateProductService(ProductWriterService writer, ProductReaderService reader) {
        return ServiceFactory.updateProductService(reader, writer);
    }

    @Bean
    public EnableProductService enableProductService(ProductWriterService writer) {
        return ServiceFactory.enableProductService(writer);
    }

    @Bean
    public DisableProductService disableProductService(ProductWriterService writer) {
        return ServiceFactory.disableProductService(writer);
    }

    @Bean
    public CreateCustomerService createCustomerService(CustomerWriterService writer, CustomerReaderService reader) {
        return ServiceFactory.createCustomerService(writer, reader);
    }

    @Bean
    public FindCustomerByDocumentService findCustomerByDocumentService (CustomerReaderService reader){
        return ServiceFactory.findCustomerByDocumentService(reader);
    }

    @Bean
    public FindCustomerByUUIDService findCustomerByUUIDService (CustomerReaderService reader){
        return ServiceFactory.findFindCustomerByUUIDService(reader);
    }

    @Bean
    public UpgradeCustomerService upgradeCustomerService(CustomerWriterService writer) {
        return ServiceFactory.upgradeCustomerService(writer);
    }

    @Bean
    public FindCartByUUIDService findCartByUUIDService(CartReaderService reader) {
        return ServiceFactory.findCartByUUIDService(reader);
    }

    @Bean
    public CreateCartService createCartService(CartWriterService writer) {
        return ServiceFactory.createCartService(writer);
    }

    @Bean
    public AddCartItemService addCartItemService(CartReaderService reader, CartWriterService writer) {
        return ServiceFactory.addCartItemService(reader, writer);
    }

    @Bean
    public UpdateCartItemService updateCartItemService(CartReaderService reader, CartWriterService writer) {
        return ServiceFactory.updateCartItemService(reader, writer);
    }

    @Bean
    public RemoveCartItemService removeCartItemService(CartReaderService reader, CartWriterService writer) {
        return ServiceFactory.removeCartItemService(reader, writer);
    }

    @Bean
    public CheckoutService checkoutService(PaymentGatewayService paymentGateway) {
        return ServiceFactory.checkoutService(paymentGateway);
    }
}