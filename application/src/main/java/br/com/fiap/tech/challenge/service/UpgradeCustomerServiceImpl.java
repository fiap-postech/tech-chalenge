package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.domain.Document;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import br.com.fiap.tech.challenge.port.driver.UpgradeCustomerService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpgradeCustomerServiceImpl implements UpgradeCustomerService {

    private final CustomerWriterService writerService;

    @Override
    public Optional<Customer> disable(String document) {
        return writerService.disable(Document.of(document));
    }
}
