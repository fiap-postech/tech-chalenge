package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.controller.product.CreateProductController;
import br.com.fiap.tech.challenge.adapter.controller.product.ProductControllerFactory;
import br.com.fiap.tech.challenge.adapter.presenter.ProductPresenter;
import br.com.fiap.tech.challenge.usecase.product.CreateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public CreateProductController createProductController(CreateProductUseCase useCase, ProductPresenter presenter){
        return ProductControllerFactory.createProductController(useCase, presenter);
    }
}
