package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.gateway.product.GatewayFactory;
import br.com.fiap.tech.challenge.adapter.repository.ProductReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.ProductWriterRepository;
import br.com.fiap.tech.challenge.gateway.product.ProductReaderGateway;
import br.com.fiap.tech.challenge.gateway.product.ProductWriterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public ProductReaderGateway productReaderGateway(ProductReaderRepository repository){
        return GatewayFactory.productReaderGateway(repository);
    }

    @Bean
    public ProductWriterGateway productWriterGateway(ProductWriterRepository repository){
        return GatewayFactory.productWriterGateway(repository);
    }

}
