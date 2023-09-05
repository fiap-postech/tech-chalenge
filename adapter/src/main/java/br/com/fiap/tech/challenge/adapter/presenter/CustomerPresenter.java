package br.com.fiap.tech.challenge.adapter.presenter;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.application.util.ResponseList;

public interface CustomerPresenter {

    CustomerDTO present(Customer customer);

    ResponseList<CustomerDTO> present(ResponseList<Customer> list);
}
