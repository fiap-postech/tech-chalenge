package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.port.driven.PaymentGatewayService;
import br.com.fiap.tech.challenge.port.driven.PurchaseReaderService;
import br.com.fiap.tech.challenge.port.driven.PurchaseWriterService;
import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.port.driver.CreatePurchaseService;
import br.com.fiap.tech.challenge.port.driver.FindAllPurchasesService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByPaymentIdService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByUUIDService;
import br.com.fiap.tech.challenge.port.driver.UpdatePurchaseService;
import br.com.fiap.tech.challenge.service.ServiceFactory;
import br.com.fiap.tech.challenge.usecase.cart.FindCartByUUIDUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public CheckoutService checkoutService(FindCartByUUIDUseCase findCartService,
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