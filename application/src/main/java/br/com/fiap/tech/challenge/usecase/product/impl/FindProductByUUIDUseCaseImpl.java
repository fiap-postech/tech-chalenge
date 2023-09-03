package br.com.fiap.tech.challenge.usecase.product.impl;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.product.ProductReaderGateway;
import br.com.fiap.tech.challenge.usecase.product.FindProductByUUIDUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class FindProductByUUIDUseCaseImpl implements FindProductByUUIDUseCase {

    private ProductReaderGateway readerService;

    @Override
    public Product get(UUID uuid) {
        return readerService.readById(uuid);
    }
}
