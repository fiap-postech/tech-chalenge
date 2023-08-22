package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.ProductEntityRepository;
import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import org.springframework.stereotype.Service;

import static br.com.fiap.tech.challenge.adapter.driven.mysql.util.Mappings.toProductEntity;
import static br.com.fiap.tech.challenge.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;

@Service
public class ProductEntityWriterService implements ProductWriterService {

    private final ProductEntityRepository repository;


    public ProductEntityWriterService(ProductEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product write(Product product) {
        if (repository.existsByUuid(product.uuid().toString())) {
            return update(product);
        }

        return insert(product);
    }

    private Product insert(Product product) {
        return repository.save(toProductEntity(product)).toDomain();
    }

    private Product update(Product product) {
        var entity = getByUUID(product.uuid().toString());

        entity.setCategory(product.category());
        entity.setName(product.name());
        entity.setPrice(priceToBigDecimalConverter(product.price()));
        entity.setImage(imageToStringConverter(product.image()));
        entity.setEnabled(product.enabled());
        entity.setDescription(product.description());

        return repository.save(entity).toDomain();
    }

    private ProductEntity getByUUID(String uuid) {
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, uuid));
    }
}
