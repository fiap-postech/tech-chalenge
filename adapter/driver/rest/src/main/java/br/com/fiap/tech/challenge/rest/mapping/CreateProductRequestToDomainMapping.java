package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.*;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.CreateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateSingleProductRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public class CreateProductRequestToDomainMapping implements RestTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CreateSingleProductRequest.class, Sandwich.class)
                .setProvider(sandwichProvider());

        mapper.typeMap(CreateSingleProductRequest.class, Dessert.class)
                .setProvider(dessertProvider());

        mapper.typeMap(CreateSingleProductRequest.class, Beverage.class)
                .setProvider(beverageProvider());

        mapper.typeMap(CreateSingleProductRequest.class, SideDish.class)
                .setProvider(sideDishProvider());
    }

    private static Provider<Sandwich> sandwichProvider() {
        return provision -> {
            var request = (CreateProductRequest) provision.getSource();

            return Sandwich.builder()
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
            var request = (CreateProductRequest) provision.getSource();

            return Dessert.builder()
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
            var request = (CreateProductRequest) provision.getSource();

            return Beverage.builder()
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
            var request = (CreateProductRequest) provision.getSource();

            return SideDish.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(request.getImage())
                    .enabled(request.isEnabled())
                    .build();
        };
    }
}
