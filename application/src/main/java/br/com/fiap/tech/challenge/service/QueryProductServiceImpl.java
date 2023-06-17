package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.port.driver.QueryProductService;

import java.util.List;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

class QueryProductServiceImpl implements QueryProductService {
    @Override
    public List<Product> allAvailable() {
        return List.of(
                Sandwich.builder()
                        .name("X-Salada")
                        .price(makeMoney(10.00))
                        .cost(makeMoney(8.00))
                        .build(),

                Sandwich.builder()
                        .name("X-Salada-Bacon")
                        .price(makeMoney(14.00))
                        .cost(makeMoney(9.00))
                        .build(),

                Beverage.builder()
                        .name("Coca cola")
                        .price(makeMoney(6.50))
                        .cost(makeMoney(5.00))
                        .build(),

                Beverage.builder()
                        .name("Suco Natural")
                        .price(makeMoney(8.50))
                        .cost(makeMoney(4.00))
                        .build(),

                SideDish.builder()
                        .name("Bata Frita")
                        .price(makeMoney(5.00))
                        .cost(makeMoney(3.00))
                        .build(),

                Dessert.builder()
                        .name("Tortinha de maça")
                        .price(makeMoney(5.50))
                        .cost(makeMoney(3.45))
                        .build()
                );
    }
}
