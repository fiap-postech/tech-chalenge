package br.com.fiap.tech.challenge.domain;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        var product = Sandwich.builder()
                .cost(Money.of(new BigDecimal("12.00"), "BRL"))
                .price(Money.of(new BigDecimal("15.00"), "BRL"))
                .name("X Salada Bacon")
                .build();

        var item = CartItem.builder()
                .product(product)
                .price(Price.of(Money.of(new BigDecimal("10.00"), "BRL")))
                .discount(Price.of(Money.of(new BigDecimal("2.00"), "BRL")))
                .quantity(Quantity.of(1))
                .build();

        System.out.println("created");
        print(item);

        item = item.incrementQuantity();

        System.out.println("incremented quantity");
        print(item);

        item = item.applyDiscount(Money.of(new BigDecimal("1.50"), "BRL"));

        System.out.println("applied discount");
        print(item);

        item = item.resetDiscount().applyDiscount(15.0);

        System.out.println("reset discount and applied through percentage");
        print(item);




    }

    private static void print(CartItem item){
        System.out.println(item.product().name());
        System.out.println(item.price());
        System.out.println(item.discount());
        System.out.println(item.quantity());
    }
}
