package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driver.CreateCartService;
import lombok.AllArgsConstructor;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CART_CUSTOMER_NOT_AVAILABLE;
import static java.util.Objects.nonNull;

@AllArgsConstructor
class CreateCartServiceImpl implements CreateCartService {

    private CartWriterService writerService;

    @Override
    public Cart create(Cart cart) {
        if (nonNull(cart.customer()) && !cart.customer().enabled()) {
            throw new ApplicationException(CART_CUSTOMER_NOT_AVAILABLE, cart.uuid(), cart.customer().uuid());
        }
        return writerService.write(cart);
    }
}