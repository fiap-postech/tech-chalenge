package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.CreateProductService;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
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
    public FindProductByUUIDService findProductByUUIDService(ProductReaderService reader) {
        return ServiceFactory.findProductByUUIDService(reader);
    }

    @Bean
    public CreateProductService createProductService(ProductWriterService writer) {
        return ServiceFactory.createProductService(writer);
    }
}
