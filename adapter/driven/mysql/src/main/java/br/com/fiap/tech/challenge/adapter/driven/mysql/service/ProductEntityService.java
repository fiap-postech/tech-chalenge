package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.ProductEntityRepository;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.fiap.tech.challenge.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;

@Service
public class ProductEntityService implements ProductWriterService, ProductReaderService {

    private final ProductEntityRepository repository;
    private final ModelMapper mapper;

    public ProductEntityService(ProductEntityRepository repository, @Qualifier("mysqlModelMapper") ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseList<Product> readAll(Page page) {
        var pageable  = PageRequest.of(page.number(), page.size());

        var result = repository.findAll(pageable);

        return new ResponseList<>(
                result.getNumber(),
                result.getSize(),
                result.getNumberOfElements(),
                result.getTotalElements(),
                result.getContent().stream().map(ProductEntity::toDomain).toList()
        );
    }

    @Override
    public Product readById(UUID id) {
        return repository.findByUuid(id.toString())
                .map(ProductEntity::toDomain)
                .orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, id.toString()));
    }

    @Override
    public Product write(Product product) {
        return repository.save(mapper.map(product, ProductEntity.class))
                .toDomain();
    }
}
