package br.com.fiap.tech.challenge.rest.mapping.product.update;

import br.com.fiap.tech.challenge.domain.entity.Product;

public interface UpdateStrategy {
    Product update(Product product);
}
