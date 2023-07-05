package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.ComboProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.entity.Beverage;
import br.com.fiap.tech.challenge.domain.entity.Combo;
import br.com.fiap.tech.challenge.domain.entity.Dessert;
import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.domain.entity.Sandwich;
import br.com.fiap.tech.challenge.domain.entity.SideDish;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;
import static java.util.Objects.isNull;

@Mapper
public class RedisProductToProductEntityMapping implements RedisTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Product.class, ProductEntity.class)
                .addMapping(Product::uuid, ProductEntity::setId)
                .addMapping(Product::name, ProductEntity::setName)
                .addMapping(Product::category, ProductEntity::setCategory)
                .addMapping(Product::description, ProductEntity::setDescription)
                .addMapping(Product::enabled, ProductEntity::setEnabled)
                .addMappings(mapping -> mapping.using(ctx -> ((Image) ctx.getSource()).url()).map(Product::image, ProductEntity::setImage))
                .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(Product::price, ProductEntity::setPrice));

        mapper.typeMap(Sandwich.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Beverage.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Dessert.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(SideDish.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Combo.class, ComboProductEntity.class)
                .includeBase(Product.class, ProductEntity.class)
                .addMappings(mapping -> {
                    var converter = comboItemsConverter();

                    mapping.using(converter).map(Combo::beverage, ComboProductEntity::setBeverage);
                    mapping.using(converter).map(Combo::sandwich, ComboProductEntity::setSandwich);
                    mapping.using(converter).map(Combo::sideDish, ComboProductEntity::setSideDish);
                });
    }

    private Converter<? extends Product, ProductEntity> comboItemsConverter(){
        return ctx -> {
            var product = ctx.getSource();

            if (isNull(product)){
                return null;
            }

            var productEntity = new ProductEntity();
            productEntity.setId(product.uuid().toString());
            productEntity.setName(product.name());
            productEntity.setDescription(product.description());
            productEntity.setPrice(product.price().amount().getNumberStripped());
            productEntity.setImage(product.image().url());
            productEntity.setCategory(product.category());
            productEntity.setEnabled(product.enabled());

            return productEntity;
        };
    }
}