package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.rest.mapping.product.update.UpdateStrategyFactory;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static br.com.fiap.tech.challenge.rest.util.Mappings.getProduct;
import static java.util.Objects.nonNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UpdateProductRequestMapper {


    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    Product toProduct(UpdateProductRequest source);


    @AfterMapping
    static void productProvider(UpdateProductRequest source, @MappingTarget Product target) {
        var product = getProduct(source.getId());
        if(nonNull(product)){
           var productUpdated = UpdateStrategyFactory.getStrategy(product.category(), source).update(product);
        }
    }
}
