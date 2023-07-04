package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.entity.Customer;

import java.util.Optional;

public interface UpgradeCustomerService {

    Optional<Customer> disable(String document);
}
