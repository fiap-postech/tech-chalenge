package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.domain.Document;

import java.util.Optional;

public interface CustomerWriterService {
    Customer write(Customer customer);
    Optional<Customer> disable (Document document);
}
