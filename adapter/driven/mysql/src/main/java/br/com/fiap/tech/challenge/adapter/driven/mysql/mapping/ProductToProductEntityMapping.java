package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.*;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToMoneyConverter;

@Mapper
public class ProductToProductEntityMapping implements MySQLTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Product.class, ProductEntity.class)
                .addMapping(Product::uuid, ProductEntity::setUuid)
                .addMapping(Product::name, ProductEntity::setName)
                .addMapping(Product::category, ProductEntity::setCategory)
                .addMapping(Product::description, ProductEntity::setDescription)
                .addMapping(Product::image, ProductEntity::setImage)
                .addMappings(mapping -> mapping.using(priceToMoneyConverter()).map(Product::price, ProductEntity::setPrice));

        mapper.typeMap(Sandwich.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Beverage.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Dessert.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(SideDish.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Combo.class, ComboEntity.class)
                .includeBase(Product.class, ProductEntity.class);
    }
}
