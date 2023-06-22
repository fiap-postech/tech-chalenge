package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.*;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.resource.request.CreateComboRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static java.util.Objects.isNull;

@Mapper
@AllArgsConstructor
public class CreateComboRequestToDomainMapping implements TypeMapConfiguration {

    private FindProductByUUIDService findProductByUUIDService;

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CreateComboRequest.class, Combo.class)
                .setProvider(comboProvider());
    }

    private Provider<Combo> comboProvider() {
        return provision -> {
            var request = (CreateComboRequest) provision.getSource();

            return Combo.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(makeMoney(request.getPrice()))
                    .image(request.getImage())
                    .beverage((Beverage) getProduct(request.getBeverageId()))
                    .sideDish((SideDish) getProduct(request.getSideDishId()))
                    .sandwich((Sandwich) getProduct(request.getSandwichId()))
                    .build();
        };
    }

    private Product getProduct(String id) {
        if (isNull(id)){
            return null;
        }

        return findProductByUUIDService.get(UUID.fromString(id));
    }
}
