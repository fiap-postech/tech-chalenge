package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.rest.mapping.product.update.UpdateStrategyFactory;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static br.com.fiap.tech.challenge.rest.util.Mappings.getProduct;
import static java.util.Objects.nonNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UpdateProductRequestMapper {

    UpdateProductRequestMapper INSTANCE = Mappers.getMapper(UpdateProductRequestMapper.class);

    default Product toProduct(UpdateProductRequest source) {
        var product = getProduct(source.getId());
        if (nonNull(product)) {
            return UpdateStrategyFactory.getStrategy(product.category(), source).update(product);
        }
        return null;
    }
}
