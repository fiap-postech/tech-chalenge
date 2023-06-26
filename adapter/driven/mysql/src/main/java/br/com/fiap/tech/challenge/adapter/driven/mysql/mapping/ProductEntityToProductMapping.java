package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Combo;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Price;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public class ProductEntityToProductMapping implements MySQLTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(ProductEntity.class, Sandwich.class)
                .setProvider(sandwichProvider());

        mapper.typeMap(ProductEntity.class, Dessert.class)
                .setProvider(dessertProvider());

        mapper.typeMap(ProductEntity.class, Beverage.class)
                .setProvider(beverageProvider());

        mapper.typeMap(ProductEntity.class, SideDish.class)
                .setProvider(sideDishProvider());

        mapper.typeMap(ComboEntity.class, Combo.class)
                .setProvider(comboProvider(mapper));
    }

    private static Provider<Sandwich> sandwichProvider() {
        return provision -> {
            var request = (ProductEntity) provision.getSource();

            return Sandwich.builder()
                    .uuid(UUID.fromString(request.getUuid()))
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .enabled(request.isEnabled())
                    .build();
        };
    }

    private static Provider<Dessert> dessertProvider() {
        return provision -> {
            var request = (ProductEntity) provision.getSource();

            return Dessert.builder()
                    .uuid(UUID.fromString(request.getUuid()))
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .enabled(request.isEnabled())
                    .build();
        };
    }

    private static Provider<Beverage> beverageProvider() {
        return provision -> {
            var request = (ProductEntity) provision.getSource();

            return Beverage.builder()
                    .uuid(UUID.fromString(request.getUuid()))
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .enabled(request.isEnabled())
                    .build();
        };
    }

    private static Provider<SideDish> sideDishProvider() {
        return provision -> {
            var request = (ProductEntity) provision.getSource();

            return SideDish.builder()
                    .uuid(UUID.fromString(request.getUuid()))
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .enabled(request.isEnabled())
                    .build();
        };
    }

    private Provider<Combo> comboProvider(ModelMapper mapper) {
        return provision -> {
            var request = (ComboEntity) provision.getSource();

            return Combo.builder()
                    .uuid(UUID.fromString(request.getUuid()))
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .beverage(mapper.map(request.getBeverage(), Beverage.class))
                    .sideDish(mapper.map(request.getSideDish(), SideDish.class))
                    .sandwich(mapper.map(request.getSandwich(), Sandwich.class))
                    .enabled(request.isEnabled())
                    .build();
        };
    }
}
