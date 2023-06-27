package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.UpdateProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UpdateProductServiceImpl implements UpdateProductService {

    private final ProductReaderService readerService;
    private final ProductWriterService writerService;

    @Override
    public Product update(Product product) {
        var storedProduct = readerService.readById(product.uuid());
        return writerService.write(storedProduct.update(product));
    }
}
