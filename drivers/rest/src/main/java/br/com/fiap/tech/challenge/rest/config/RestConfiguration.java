package br.com.fiap.tech.challenge.rest.config;

import br.com.fiap.tech.challenge.rest.mapping.AddCartItemRequestMapper;
import br.com.fiap.tech.challenge.rest.util.Mappings;
import br.com.fiap.tech.challenge.usecase.product.FindProductByUUIDUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("br.com.fiap.tech.challenge.rest")
public class RestConfiguration {

    @Bean("restMappings")
    @Autowired
    public Mappings loadMappings(FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return Mappings.init(findProductByUUIDUseCase);
    }

    @Bean("restMappingAddCart")
    @Autowired
    public AddCartItemRequestMapper loadMappingAddCart(FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return AddCartItemRequestMapper.init(findProductByUUIDUseCase);
    }

}
