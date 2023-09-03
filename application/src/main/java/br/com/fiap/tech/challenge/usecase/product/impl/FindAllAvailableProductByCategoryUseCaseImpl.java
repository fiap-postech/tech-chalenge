package br.com.fiap.tech.challenge.usecase.product.impl;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.gateway.product.ProductReaderGateway;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductByCategoryUseCase;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindAllAvailableProductByCategoryUseCaseImpl implements FindAllAvailableProductByCategoryUseCase {

    private final ProductReaderGateway readerService;

    @Override
    public ResponseList<Product> list(ProductCategory category, Page page) {
        return readerService.readAllByCategory(category, page);
    }
}
