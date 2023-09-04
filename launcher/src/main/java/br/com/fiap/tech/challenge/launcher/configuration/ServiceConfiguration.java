package br.com.fiap.tech.challenge.launcher.configuration;

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
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByPaymentIdService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByUUIDService;
import br.com.fiap.tech.challenge.port.driver.RemoveCartItemService;
import br.com.fiap.tech.challenge.port.driver.UpdateCartItemService;
import br.com.fiap.tech.challenge.port.driver.UpdatePurchaseService;
import br.com.fiap.tech.challenge.service.ServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

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
    public CheckoutService checkoutService(FindCartByUUIDService findCartService,
                                           CreatePurchaseService purchaseService,
                                           PaymentGatewayService paymentGateway) {
        return ServiceFactory.checkoutService(findCartService, purchaseService, paymentGateway);
    }

    @Bean
    public CreatePurchaseService createPurchaseService(PurchaseWriterService writer) {
        return ServiceFactory.createPurchaseService(writer);
    }

    @Bean
    public UpdatePurchaseService updatePurchaseService(PurchaseWriterService writer) {
        return ServiceFactory.updatePurchaseService(writer);
    }

    @Bean
    public FindAllPurchasesService findAllPurchasesService(PurchaseReaderService reader) {
        return ServiceFactory.findAllPurchasesService(reader);
    }

    @Bean
    public FindPurchaseByUUIDService findPurchaseByUUIDService(PurchaseReaderService reader) {
        return ServiceFactory.findPurchaseByUUIDService(reader);
    }

    @Bean
    public FindPurchaseByPaymentIdService findPurchaseByPaymentIdService(PaymentGatewayService paymentGateway,
                                                                         PurchaseReaderService readerService) {
        return ServiceFactory.findPurchaseByPaymentIdService(paymentGateway, readerService);
    }
}