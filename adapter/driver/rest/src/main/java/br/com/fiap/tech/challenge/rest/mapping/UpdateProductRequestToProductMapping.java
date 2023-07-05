package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.mapping.product.update.UpdateStrategyFactory;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

@Mapper
@RequiredArgsConstructor
public class UpdateProductRequestToProductMapping implements RestTypeMapConfiguration {

    private final FindProductByUUIDService findProductService;

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(UpdateProductRequest.class, Product.class)
                .setProvider(productProvider());
    }

    private Provider<Product> productProvider() {
        return provision -> {
            var request = (UpdateProductRequest) provision.getSource();

            var product = findProductService.get(UUID.fromString(request.getId()));

            return UpdateStrategyFactory.getStrategy(product.category(), request).update(product);
        };
    }
}
