package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class FindCartByUUIDUseCaseImpl implements FindCartByUUIDUseCase {

    private CartReaderService readerService;

    @Override
    public Cart get(UUID uuid) {
        return readerService.readById(uuid);
    }
}