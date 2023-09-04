package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.dto.UpdateCartItemDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;

import java.util.UUID;

public interface UpdateCartItemUseCase {

    Cart update(UUID uuid, UpdateCartItemDTO dto);

}