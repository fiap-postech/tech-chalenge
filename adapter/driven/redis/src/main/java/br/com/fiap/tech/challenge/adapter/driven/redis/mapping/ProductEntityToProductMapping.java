package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Price;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public class ProductEntityToProductMapping implements TypeMapConfiguration {

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
            var request = (ProductEntity) provision.getSource();

            return Sandwich.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .build();
        };
    }

    private static Provider<Dessert> dessertProvider() {
        return provision -> {
            var request = (ProductEntity) provision.getSource();

            return Dessert.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .build();
        };
    }

    private static Provider<Beverage> beverageProvider() {
        return provision -> {
            var request = (ProductEntity) provision.getSource();

            return Beverage.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .build();
        };
    }

    private static Provider<SideDish> sideDishProvider() {
        return provision -> {
            var request = (ProductEntity) provision.getSource();

            return SideDish.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .build();
        };
    }
}