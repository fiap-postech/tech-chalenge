package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Customer;

public interface CreateCustomerService {
    Customer create(Customer customer);
}
