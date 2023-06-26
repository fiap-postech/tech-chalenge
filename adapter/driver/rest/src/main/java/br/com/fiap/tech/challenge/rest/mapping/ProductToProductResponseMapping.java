package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Combo;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.ComboResponse;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToMoneyConverter;

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
                    mapping.using(priceToMoneyConverter()).map(Product::price, ProductResponse::setPrice);
                    mapping.using(imageToStringConverter()).map(Product::image, ProductResponse::setImage);
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
