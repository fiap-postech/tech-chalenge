package br.com.fiap.tech.challenge.usecase.customer;

import br.com.fiap.tech.challenge.dto.CreateCustomerDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;

public interface CreateCustomerUseCase {
    Customer create(CreateCustomerDTO dto);
}
