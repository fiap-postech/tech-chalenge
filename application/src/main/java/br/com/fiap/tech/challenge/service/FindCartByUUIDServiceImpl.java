package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driver.FindCartByUUIDService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class FindCartByUUIDServiceImpl implements FindCartByUUIDService {

    private CartReaderService readerService;

    @Override
    public Cart get(UUID uuid) {
        return readerService.readById(uuid);
    }
}