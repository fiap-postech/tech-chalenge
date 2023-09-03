package br.com.fiap.tech.challenge.usecase.product;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.gateway.ProductReaderGateway;
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
