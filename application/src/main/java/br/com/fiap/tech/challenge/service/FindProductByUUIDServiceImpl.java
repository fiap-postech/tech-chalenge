package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class FindProductByUUIDServiceImpl implements FindProductByUUIDService {

    private ProductReaderService readerService;

    @Override
    public Product get(UUID uuid) {
        return readerService.readById(uuid);
    }
}
