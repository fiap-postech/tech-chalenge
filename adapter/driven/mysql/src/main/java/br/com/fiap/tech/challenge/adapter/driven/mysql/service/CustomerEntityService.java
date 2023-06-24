package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.CustomerEntityRepository;
import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerEntityService implements CustomerWriterService, CustomerReaderService {

    private CustomerEntityRepository repository;
    private ModelMapper mapper;

    @Override
    public Customer write(Customer customer) {
        return repository.save(mapper.map(customer, CustomerEntity.class))
                .toDomain();
    }

    @Override
    public Optional<Customer> readByDocument(String document) {
        return repository.findByDocument(document)
                .map(CustomerEntity::toDomain);
    }
}
