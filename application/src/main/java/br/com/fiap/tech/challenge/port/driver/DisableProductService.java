package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.entity.Product;

public interface DisableProductService {
    Product disable(Product product);
}
