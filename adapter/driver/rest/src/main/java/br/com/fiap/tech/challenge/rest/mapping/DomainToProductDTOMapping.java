package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.util.Moneys;
import org.javamoney.moneta.Money;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.domain.MoneyConstants.CURRENCY_PRECISION;
import static br.com.fiap.tech.challenge.domain.MoneyConstants.CURRENCY_ROUNDING_MODE;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Mapper
public class DomainToProductDTOMapping implements TypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Product.class, ProductResponse.class)
                .addMapping(Product::uuid, ProductResponse::setId)
                .addMapping(Product::name, ProductResponse::setName)
                .addMapping(Product::type, ProductResponse::setType)
                .addMappings(mapping -> {
                    mapping.using(priceToMoneyConverter()).map(Product::cost, ProductResponse::setCost);
                    mapping.using(priceToMoneyConverter()).map(Product::price, ProductResponse::setPrice);
                    mapping.using(priceToMoneyConverter()).map(Product::profit, ProductResponse::setProfit);
                });

        mapper.typeMap(Sandwich.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(Beverage.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(Dessert.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);

        mapper.typeMap(SideDish.class, ProductResponse.class)
                .includeBase(Product.class, ProductResponse.class);
    }

    private Converter<Money, BigDecimal> priceToMoneyConverter() {
        return ctx -> defaultIfNull(ctx.getSource(), Moneys.zero())
                .getNumberStripped()
                .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE);
    }
}
