package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Combo;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.CreateComboService;
import br.com.fiap.tech.challenge.port.driver.CreateProductService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CreateComboServiceImpl implements CreateComboService {

    private ProductWriterService writerService;

    @Override
    public Combo create(Combo combo) {
        return null;
    }
}
