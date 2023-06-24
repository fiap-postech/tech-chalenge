package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Customer;

public interface CustomerWriterService {
    Customer write(Customer customer);
}
