package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.*;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;

@Mapper
public class ProductToProductResponseMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Product.class, ProductResponse.class)
                .addMapping(Product::uuid, ProductResponse::setId)
                .addMapping(Product::name, ProductResponse::setName)
                .addMapping(Product::category, ProductResponse::setCategory)
                .addMapping(Product::description, ProductResponse::setDescription)
                .addMapping(Product::image, ProductResponse::setImage)
                .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(Product::price, ProductResponse::setPrice));

        mapper.typeMap(Sandwich.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(Beverage.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(Dessert.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(SideDish.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);
    }
}
