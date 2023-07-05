package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.entity.Customer;

import java.util.UUID;

public interface FindCustomerByUUIDService {

    Customer get(UUID uuid);

}