package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.enterprise.entity.Product;

import java.util.UUID;

public interface FindProductByUUIDService {

    Product get(UUID uuid);

}
