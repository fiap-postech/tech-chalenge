package br.com.fiap.tech.challenge.rest.util;

import br.com.fiap.tech.challenge.domain.Combo;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.rest.resource.response.ComboResponse;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {
    public static ProductResponse toProductResponse(ModelMapper mapper, Product product) {
        if (isNull(product)) {
            return null;
        }

        if (product instanceof Combo){
            return mapper.map(product, ComboResponse.class);
        }

        return mapper.map(product, ProductResponse.class);
    }
}
