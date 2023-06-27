package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;

@Mapper
public class ProductToProductEntityMapping implements TypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Product.class, ProductEntity.class)
                .addMapping(Product::uuid, ProductEntity::setId)
                .addMapping(Product::name, ProductEntity::setName)
                .addMapping(Product::category, ProductEntity::setCategory)
                .addMapping(Product::description, ProductEntity::setDescription)
                .addMapping(Product::image, ProductEntity::setImage)
                .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(Product::price, ProductEntity::setPrice));

        mapper.typeMap(Sandwich.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Beverage.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Dessert.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(SideDish.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);
    }
}