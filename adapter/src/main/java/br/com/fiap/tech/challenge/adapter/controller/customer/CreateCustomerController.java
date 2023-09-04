package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.dto.CreateCustomerDTO;

public interface CreateCustomerController {

    CustomerDTO create(CreateCustomerDTO dto);

}
