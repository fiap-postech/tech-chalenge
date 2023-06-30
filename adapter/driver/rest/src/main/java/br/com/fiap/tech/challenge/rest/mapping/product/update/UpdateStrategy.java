package br.com.fiap.tech.challenge.rest.mapping.product.update;

import br.com.fiap.tech.challenge.domain.Product;

public interface UpdateStrategy {
    Product update(Product product);
}
