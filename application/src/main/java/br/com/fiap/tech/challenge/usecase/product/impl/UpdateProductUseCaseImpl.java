package br.com.fiap.tech.challenge.usecase.product.impl;

import br.com.fiap.tech.challenge.dto.UpdateProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.product.ProductReaderGateway;
import br.com.fiap.tech.challenge.gateway.product.ProductWriterGateway;
import br.com.fiap.tech.challenge.usecase.product.UpdateProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.impl.update.UpdateStrategyFactory;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductReaderGateway readerService;
    private final ProductWriterGateway writerService;

    @Override
    public Product update(UpdateProductDTO request) {
        var storedProduct = readerService.readById(UUID.fromString(request.getId()));

        var strategy = UpdateStrategyFactory.getStrategy(storedProduct.category(), request);

        return writerService.write(strategy.update(storedProduct));
    }
}
