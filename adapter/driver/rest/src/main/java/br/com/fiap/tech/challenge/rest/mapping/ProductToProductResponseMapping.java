package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.*;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.ComboResponse;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.discountToBigDecimalConverter;

@Mapper
public class ProductToProductResponseMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Product.class, ProductResponse.class)
                .addMapping(Product::uuid, ProductResponse::setId)
                .addMapping(Product::name, ProductResponse::setName)
                .addMapping(Product::category, ProductResponse::setCategory)
                .addMapping(Product::description, ProductResponse::setDescription)
                .addMapping(Product::enabled, ProductResponse::setEnabled)
                .addMappings(mapping -> {
 //                   mapping.using(priceToBigDecimalConverter()).map(Product::price, ProductResponse::setPrice);
 //                   mapping.using(priceToBigDecimalConverter()).map(Product::fullPrice, ProductResponse::setFullPrice);
                    mapping.using(discountToBigDecimalConverter()).map(Product::discount, ProductResponse::setDiscount);
  //                  mapping.using(imageToStringConverter()).map(Product::image, ProductResponse::setImage);
                });

        mapper.typeMap(Sandwich.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(Beverage.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(Dessert.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(SideDish.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(Combo.class, ComboResponse.class)
                .includeBase(Product.class, ProductResponse.class)
                .addMapping(Combo::beverage, ComboResponse::setBeverage)
                .addMapping(Combo::sandwich, ComboResponse::setSandwich)
                .addMapping(Combo::sideDish, ComboResponse::setSideDish);
    }
}
