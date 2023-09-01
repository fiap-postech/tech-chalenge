package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductService;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class FindAllAvailableProductServiceImpl implements FindAllAvailableProductService {

    private ProductReaderService reader;

    @Override
    public ResponseList<Product> list(Page page) {
        return reader.readAll(page);
    }
}
