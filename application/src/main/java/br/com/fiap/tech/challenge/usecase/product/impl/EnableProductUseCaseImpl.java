package br.com.fiap.tech.challenge.usecase.product.impl;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.product.ProductReaderGateway;
import br.com.fiap.tech.challenge.gateway.product.ProductWriterGateway;
import br.com.fiap.tech.challenge.usecase.product.EnableProductUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class EnableProductUseCaseImpl implements EnableProductUseCase {

    private final ProductReaderGateway readerGateway;
    private final ProductWriterGateway writerGateway;

    @Override
    public Product enable(String uuid) {
        var product = readerGateway.readById(UUID.fromString(uuid));
        return writerGateway.write(product.enable());
    }
}
