package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.port.driver.ProductReaderService;
import br.com.fiap.tech.challenge.service.ServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ProductReaderService queryProductService() {
        return ServiceFactory.productReaderService();
    }
}
