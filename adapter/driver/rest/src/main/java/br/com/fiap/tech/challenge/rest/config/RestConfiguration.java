package br.com.fiap.tech.challenge.rest.config;

import br.com.fiap.tech.challenge.port.driver.FindCustomerByUUIDService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.mapping.AddCartItemRequestMapper;
import br.com.fiap.tech.challenge.rest.mapping.CreateCartRequestMapper;
import br.com.fiap.tech.challenge.rest.util.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("br.com.fiap.tech.challenge.rest")
public class RestConfiguration {

    @Bean("restMappings")
    @Autowired
    public Mappings loadMappings(FindProductByUUIDService findProductByUUIDService) {
        return Mappings.init(findProductByUUIDService);
    }

    @Bean("restMappingCustomer")
    @Autowired
    public CreateCartRequestMapper loadMappingCustomer(FindCustomerByUUIDService findCustomerByUUIDService) {
        return CreateCartRequestMapper.init(findCustomerByUUIDService);
    }

    @Bean("restMappingAddCart")
    @Autowired
    public AddCartItemRequestMapper loadMappingAddCart(FindProductByUUIDService findProductByUUIDService) {
        return AddCartItemRequestMapper.init(findProductByUUIDService);
    }

}
