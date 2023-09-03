package br.com.fiap.tech.challenge.adapter.controller.product;

import br.com.fiap.tech.challenge.adapter.presenter.ProductPresenter;
import br.com.fiap.tech.challenge.usecase.product.CreateProductUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductControllerFactory {

    public static CreateProductController createProductController(CreateProductUseCase useCase, ProductPresenter presenter){
        return new ProductController(useCase, presenter);
    }

}
