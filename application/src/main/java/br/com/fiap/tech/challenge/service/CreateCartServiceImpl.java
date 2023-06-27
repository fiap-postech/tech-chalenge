package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Discount;
import br.com.fiap.tech.challenge.domain.Price;
import br.com.fiap.tech.challenge.domain.Quantity;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driver.CreateCartService;
import br.com.fiap.tech.challenge.util.Moneys;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class CreateCartServiceImpl implements CreateCartService {

    private CartWriterService writerService;

    @Override
    public Cart create(Cart cart) {
        return writerService.write(cart);

//        var dessert = new Dessert(UUID.randomUUID(), "Torta de Maçã", "Torta", Price.of(Moneys.makeMoney(10.99D)), "link");
//        var quantity = Quantity.of(2);
//
//        var total = dessert.price().amount().multiply(quantity.value());
//        var cartItem = new CartItem(dessert, Price.of(total), Discount.withoutDiscount(), quantity);
//        cart.addItem(cartItem);
//
//        return cart;
    }
}