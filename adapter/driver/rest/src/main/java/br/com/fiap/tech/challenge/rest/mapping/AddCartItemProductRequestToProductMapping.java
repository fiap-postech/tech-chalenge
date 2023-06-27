package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Image;
import br.com.fiap.tech.challenge.domain.Price;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemProductRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public class AddCartItemProductRequestToProductMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(AddCartItemProductRequest.class, Sandwich.class)
                .setProvider(sandwichProvider());

        mapper.typeMap(AddCartItemProductRequest.class, Dessert.class)
                .setProvider(dessertProvider());

        mapper.typeMap(AddCartItemProductRequest.class, Beverage.class)
                .setProvider(beverageProvider());

        mapper.typeMap(AddCartItemProductRequest.class, SideDish.class)
                .setProvider(sideDishProvider());
    }

    private static Provider<Sandwich> sandwichProvider() {
        return provision -> {
            var request = (AddCartItemProductRequest) provision.getSource();
            return Sandwich.builder()
                    .uuid(UUID.fromString(request.getId()))
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(Image.of(request.getImage()))
                    .build();
        };
    }

    private static Provider<Dessert> dessertProvider() {
        return provision -> {
            var request = (AddCartItemProductRequest) provision.getSource();
            return Dessert.builder()
                    .uuid(UUID.fromString(request.getId()))
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(Image.of(request.getImage()))
                    .build();
        };
    }

    private static Provider<Beverage> beverageProvider() {
        return provision -> {
            var request = (AddCartItemProductRequest) provision.getSource();
            return Beverage.builder()
                    .uuid(UUID.fromString(request.getId()))
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(Image.of(request.getImage()))
                    .build();
        };
    }

    private static Provider<SideDish> sideDishProvider() {
        return provision -> {
            var request = (AddCartItemProductRequest) provision.getSource();
            return SideDish.builder()
                    .uuid(UUID.fromString(request.getId()))
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(Price.of(makeMoney(request.getPrice())))
                    .image(Image.of(request.getImage()))
                    .build();
        };
    }
}
