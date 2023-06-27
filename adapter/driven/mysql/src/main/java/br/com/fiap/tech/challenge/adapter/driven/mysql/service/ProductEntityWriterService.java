package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.ProductEntityRepository;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLModelMapperConfiguration.MYSQL_MODEL_MAPPER;
import static br.com.fiap.tech.challenge.adapter.driven.mysql.util.Mappings.toProductEntity;
import static br.com.fiap.tech.challenge.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;

@Service
public class ProductEntityWriterService implements ProductWriterService {

    private final ProductEntityRepository repository;
    private final ModelMapper mapper;

    public ProductEntityWriterService(ProductEntityRepository repository, @Qualifier(MYSQL_MODEL_MAPPER) ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Product write(Product product) {
        if (repository.existsByUuid(product.uuid().toString())){
            return update(product);
        }

        return insert(product);
    }

    private Product insert(Product product) {
        return repository.save(toProductEntity(mapper, product)).toDomain(mapper);
    }

    private Product update(Product product) {
        var entity = getByUUID(product.uuid().toString());

        mapper.map(product, entity);

//        entity.setEnabled(product.enabled());
//        entity.setName(product.name());
//        entity.setDescription(product.description());
//        entity.setPrice(product.price().amount().getNumberStripped());
//        entity.setImage(product.image().url());

        return repository.save(entity).toDomain(mapper);
    }

    private ProductEntity getByUUID(String uuid){
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, uuid));
    }
}
