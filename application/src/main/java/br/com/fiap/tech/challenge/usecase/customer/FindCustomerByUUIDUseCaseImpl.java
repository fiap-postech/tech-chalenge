package br.com.fiap.tech.challenge.usecase.customer;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.gateway.CustomerReaderGateway;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class FindCustomerByUUIDUseCaseImpl implements FindCustomerByUUIDUseCase {

    private CustomerReaderGateway customerReaderGateway;

    @Override
    public Customer get(UUID uuid) {
        return customerReaderGateway.readById(uuid);
    }
}