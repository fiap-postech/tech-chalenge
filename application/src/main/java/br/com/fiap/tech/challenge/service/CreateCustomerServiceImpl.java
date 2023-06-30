package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import br.com.fiap.tech.challenge.port.driver.CreateCustomerService;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.tech.challenge.error.ApplicationError.CUSTOMER_HAS_REGISTRATION;

@RequiredArgsConstructor
class CreateCustomerServiceImpl implements CreateCustomerService {

    private final CustomerWriterService writerService;
    private final CustomerReaderService readerService;
    @Override
    public Customer create(Customer customer) {
        var customerOpt = readerService.readByDocument(customer.document());
        customerOpt.ifPresent(customerResult -> {throw new ApplicationException(CUSTOMER_HAS_REGISTRATION);});
        return writerService.write(customer);
    }
}