package br.com.fiap.tech.challenge.usecase.product.impl;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.product.ProductWriterGateway;
import br.com.fiap.tech.challenge.usecase.product.EnableProductUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class EnableProductUseCaseImpl implements EnableProductUseCase {

    private final ProductWriterGateway writerService;

    @Override
    public Product enable(Product product) {
        return writerService.write(product.enable());
    }
}
