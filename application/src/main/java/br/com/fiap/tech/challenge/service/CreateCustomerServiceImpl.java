package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import br.com.fiap.tech.challenge.port.driver.CreateCustomerService;
import lombok.AllArgsConstructor;

import static br.com.fiap.tech.challenge.error.ApplicationError.CUSTOMER_HAS_REGISTRATION;

@AllArgsConstructor
public class CreateCustomerServiceImpl implements CreateCustomerService {

    private CustomerWriterService writerService;
    private CustomerReaderService readerService;
    //TODO: validação se customer ja e cadastrado, deixei na Application, quem tem que garantir que nao se cadastre customer duplicado?
    @Override
    public Customer create(Customer customer) {
        var customerOpt = readerService.readByDocument(customer.document());
        return customerOpt
                .<Customer>map(customerResult -> { throw new ApplicationException(CUSTOMER_HAS_REGISTRATION); })
                .orElseGet(() -> writerService.write(customer));
    }
}
