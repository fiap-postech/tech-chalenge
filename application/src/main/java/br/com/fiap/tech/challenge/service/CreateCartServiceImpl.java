package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driver.CreateCartService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CreateCartServiceImpl implements CreateCartService {

    private CartWriterService writerService;

    @Override
    public Cart create(Cart cart) {
        return writerService.write(cart);
    }
}