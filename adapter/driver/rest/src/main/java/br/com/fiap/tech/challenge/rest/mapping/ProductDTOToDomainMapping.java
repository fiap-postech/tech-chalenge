package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static java.util.Objects.nonNull;

@Mapper
public class ProductDTOToDomainMapping implements TypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(ProductResponse.class, Sandwich.class)
                .setProvider(sandwichProvider());
    }

    private static Provider<Sandwich> sandwichProvider(){
        return provision -> {
            var dto = (ProductResponse) provision.getSource();

            var builder = Sandwich.builder();

            builder.cost(makeMoney(dto.getCost()))
                    .name(dto.getName())
                    .price(makeMoney(dto.getPrice()));

            if (nonNull(dto.getId())){
                builder.uuid(UUID.fromString(dto.getId()));
            }

            return builder.build();
        };
    }
}
