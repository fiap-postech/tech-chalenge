package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.EnableProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class EnableProductServiceImpl implements EnableProductService {

    private final ProductWriterService writerService;

    @Override
    public Product enable(Product product) {
        return writerService.enable(product);
    }
}
