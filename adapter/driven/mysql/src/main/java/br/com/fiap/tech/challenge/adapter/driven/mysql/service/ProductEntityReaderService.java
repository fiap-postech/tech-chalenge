package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.ProductEntityRepository;
import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.domain.enums.ProductCategory;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Function;

import static br.com.fiap.tech.challenge.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;

@Service
public class ProductEntityReaderService implements ProductReaderService {

    private final ProductEntityRepository repository;

    public ProductEntityReaderService(ProductEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseList<Product> readAll(Page page) {
        return readAll(page, repository::findAllByEnabledTrue);
    }

    @Override
    public ResponseList<Product> readAllByCategory(ProductCategory category, Page page) {
        return readAll(page, pageable -> repository.findAllByEnabledTrueAndCategory(category, pageable));
    }

    @Override
    public Product readById(UUID id) {
        return repository.findByUuid(id.toString())
                .map(ProductEntity::toDomain)
                .orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, id.toString()));
    }

    private ResponseList<Product> readAll(Page page, Function<Pageable, org.springframework.data.domain.Page<ProductEntity>> reader) {
        var result = reader.apply(PageRequest.of(page.number(), page.size()));

        return new ResponseList<>(
                result.getNumber(),
                result.getSize(),
                result.getNumberOfElements(),
                result.getTotalElements(),
                result.getContent().stream().map(ProductEntity::toDomain).toList()
        );
    }
}
