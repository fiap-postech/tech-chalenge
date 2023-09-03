package br.com.fiap.tech.challenge.usecase.product.impl;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.product.ProductReaderGateway;
import br.com.fiap.tech.challenge.gateway.product.ProductWriterGateway;
import br.com.fiap.tech.challenge.usecase.product.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductReaderGateway readerService;
    private final ProductWriterGateway writerService;

    @Override
    public Product update(Product product) {
        var storedProduct = readerService.readById(product.uuid());
        return writerService.write(storedProduct.update(product));
    }
}
