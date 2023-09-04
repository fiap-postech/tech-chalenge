package br.com.fiap.tech.challenge.usecase.product;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.gateway.ProductWriterGateway;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class DisableProductUseCaseImpl implements DisableProductUseCase {

    private final ProductReaderGateway readerGateway;
    private final ProductWriterGateway writerGateway;

    @Override
    public Product disable(String uuid) {
        var product = readerGateway.readById(UUID.fromString(uuid));
        return writerGateway.write(product.disable());
    }
}
