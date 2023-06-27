package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Image;
import br.com.fiap.tech.challenge.domain.Price;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public class ProductEntityToProductMapping implements RedisTypeMapConfiguration {

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
    }

    private static Provider<Sandwich> sandwichProvider() {
        return provision -> {
            var source = (ProductEntity) provision.getSource();

            return Sandwich.builder()
                    .uuid(UUID.fromString(source.getId()))
                    .name(source.getName())
                    .description(source.getDescription())
                    .price(Price.of(makeMoney(source.getPrice())))
                    .image(Image.of(source.getImage()))
                    .build();
        };
    }

    private static Provider<Dessert> dessertProvider() {
        return provision -> {
            var source = (ProductEntity) provision.getSource();

            return Dessert.builder()
                    .uuid(UUID.fromString(source.getId()))
                    .name(source.getName())
                    .description(source.getDescription())
                    .price(Price.of(makeMoney(source.getPrice())))
                    .image(Image.of(source.getImage()))
                    .build();
        };
    }

    private static Provider<Beverage> beverageProvider() {
        return provision -> {
            var source = (ProductEntity) provision.getSource();

            return Beverage.builder()
                    .uuid(UUID.fromString(source.getId()))
                    .name(source.getName())
                    .description(source.getDescription())
                    .price(Price.of(makeMoney(source.getPrice())))
                    .image(Image.of(source.getImage()))
                    .build();
        };
    }

    private static Provider<SideDish> sideDishProvider() {
        return provision -> {
            var source = (ProductEntity) provision.getSource();

            return SideDish.builder()
                    .uuid(UUID.fromString(source.getId()))
                    .name(source.getName())
                    .description(source.getDescription())
                    .price(Price.of(makeMoney(source.getPrice())))
                    .image(Image.of(source.getImage()))
                    .build();
        };
    }
}