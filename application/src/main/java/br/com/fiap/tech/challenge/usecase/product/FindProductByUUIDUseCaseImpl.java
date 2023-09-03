package br.com.fiap.tech.challenge.usecase.product;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.ProductReaderGateway;
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
