package br.com.fiap.tech.challenge.usecase.product;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;

public interface FindAllAvailableProductUseCase {

    ResponseList<Product> list(Page page);

}
