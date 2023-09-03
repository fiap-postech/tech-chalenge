package br.com.fiap.tech.challenge.usecase.product.impl;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.product.ProductReaderGateway;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductUseCase;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class FindAllAvailableProductUseCaseImpl implements FindAllAvailableProductUseCase {

    private ProductReaderGateway reader;

    @Override
    public ResponseList<Product> list(Page page) {
        return reader.readAll(page);
    }
}
