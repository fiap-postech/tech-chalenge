package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.entity.Customer;

public interface CreateCustomerService {
    Customer create(Customer customer);
}
