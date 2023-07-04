package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.entity.Cart;

public interface CreateCartService {

    Cart create(Cart cart);

}