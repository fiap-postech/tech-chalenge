package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.mapping.CustomerMapperMySQL;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.CustomerEntityRepository;
import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.domain.valueobject.Document;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;

@Service
public class CustomerEntityService implements CustomerWriterService, CustomerReaderService {

    private final CustomerEntityRepository repository;
    private final CustomerMapperMySQL mapper;

    public CustomerEntityService(CustomerEntityRepository repository, CustomerMapperMySQL mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Customer readById(UUID id) {
        return repository.findByUuid(id.toString())
                .map(CustomerEntity::toDomain)
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, id.toString()));
    }

    @Override
    public Customer write(Customer customer) {
        return repository.save(mapper.toCustomerEntity(customer))
                .toDomain();
    }

    @Override
    public Optional<Customer> disable(Document document) {
        var customerEntityOpt = getCustomerEntity(document);
        return customerEntityOpt.flatMap(this::updateCustomer);
    }

    @Override
    public Optional<Customer> readByDocument(Document document) {
        return getCustomerEntity(document)
                .map(CustomerEntity::toDomain);
    }

    private Optional<Customer> updateCustomer(CustomerEntity customerEntity) {
        customerEntity.setEnabled(Boolean.FALSE);
        return Optional.of(repository.saveAndFlush(customerEntity).toDomain());
    }

    private Optional<CustomerEntity> getCustomerEntity(Document document) {
        return repository.findByDocumentAndEnabled(document.document(), Boolean.TRUE);
    }
}