package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.DisableProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DisableProductServiceImpl implements DisableProductService {

    private final ProductWriterService writerService;

    @Override
    public Product disable(Product product) {
        return writerService.write(product.disable());
    }
}
