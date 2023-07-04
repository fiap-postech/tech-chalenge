package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByUUIDService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class FindCustomerByUUIDServiceImpl implements FindCustomerByUUIDService {

    private CustomerReaderService customerReaderService;

    @Override
    public Customer get(UUID uuid) {
        return customerReaderService.readById(uuid);
    }
}