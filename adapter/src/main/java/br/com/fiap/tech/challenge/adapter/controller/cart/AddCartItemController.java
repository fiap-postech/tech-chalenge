package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.dto.AddCartItemDTO;

public interface AddCartItemController {
    CartDTO add(String uuid, AddCartItemDTO item);
}
