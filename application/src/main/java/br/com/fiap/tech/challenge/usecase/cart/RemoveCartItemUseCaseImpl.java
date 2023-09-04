package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.dto.RemoveCartItemDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.gateway.CartWriterGateway;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static java.util.UUID.fromString;

@AllArgsConstructor
public class RemoveCartItemUseCaseImpl implements RemoveCartItemUseCase {

    private CartReaderGateway cartReaderGateway;
    private CartWriterGateway writerService;

    @Override
    public Cart remove(UUID uuid, RemoveCartItemDTO dto) {
        var cart = cartReaderGateway.readById(uuid);
        return writerService.write(cart.removeItem(fromString(dto.getProductId())));
    }
}