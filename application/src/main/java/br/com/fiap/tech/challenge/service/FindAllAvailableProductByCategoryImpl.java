package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.ProductCategory;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductByCategory;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindAllAvailableProductByCategoryImpl implements FindAllAvailableProductByCategory {

    private final ProductReaderService readerService;

    @Override
    public ResponseList<Product> list(ProductCategory category, Page page) {
        return readerService.readAllByCategory(category, page);
    }
}
