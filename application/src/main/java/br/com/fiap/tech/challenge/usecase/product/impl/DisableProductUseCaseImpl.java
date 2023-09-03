package br.com.fiap.tech.challenge.usecase.product.impl;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.product.ProductWriterGateway;
import br.com.fiap.tech.challenge.usecase.product.DisableProductUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DisableProductUseCaseImpl implements DisableProductUseCase {

    private final ProductWriterGateway writerService;

    @Override
    public Product disable(Product product) {
        return writerService.write(product.disable());
    }
}
