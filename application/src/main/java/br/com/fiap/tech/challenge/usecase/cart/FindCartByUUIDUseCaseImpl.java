package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.gateway.CartReaderGateway;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class FindCartByUUIDUseCaseImpl implements FindCartByUUIDUseCase {

    private CartReaderGateway readerService;

    @Override
    public Cart get(UUID uuid) {
        return readerService.readById(uuid);
    }
}