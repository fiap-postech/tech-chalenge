package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.CustomerEntityRepository;
import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.domain.Document;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLModelMapperConfiguration.MYSQL_MODEL_MAPPER;

@Service
public class CustomerEntityService implements CustomerWriterService, CustomerReaderService {

    private final CustomerEntityRepository repository;
    private final ModelMapper mapper;

    public CustomerEntityService(CustomerEntityRepository repository, @Qualifier(MYSQL_MODEL_MAPPER) ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Customer write(Customer customer) {
        return repository.save(mapper.map(customer, CustomerEntity.class))
                .toDomain();
    }

    @Override
    public Optional<Customer> disable(Document document) {
        var customerEntityOpt = getCustomerEntity(document);
        return customerEntityOpt.flatMap(this::updateCustomer);
    }

    private Optional<Customer> updateCustomer(CustomerEntity customerEntity) {
        customerEntity.setEnabled(Boolean.FALSE);
        return Optional.of(repository.saveAndFlush(customerEntity).toDomain());
    }

    @Override
    public Optional<Customer> readByDocument(Document document) {
        return getCustomerEntity(document)
                .map(CustomerEntity::toDomain);
    }
    private Optional<CustomerEntity> getCustomerEntity(Document document) {
        return repository.findByDocumentAndEnabled(document.document(), Boolean.TRUE);
    }
}
