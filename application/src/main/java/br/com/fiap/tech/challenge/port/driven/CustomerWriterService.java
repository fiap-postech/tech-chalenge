package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.domain.valueobject.Document;

import java.util.Optional;

public interface CustomerWriterService {
    Customer write(Customer customer);
    Optional<Customer> disable (Document document);
}
