package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.dto.CreateCartDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;

public interface CreateCartUseCase {

    Cart create(CreateCartDTO dto);

}