package br.com.fiap.tech.challenge.rest.mapping.product.update;

import br.com.fiap.tech.challenge.domain.ProductCategory;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateStrategyFactory {
    public static UpdateStrategy getStrategy(ProductCategory category, UpdateProductRequest request){
        return switch (category){
            case COMBO -> new ComboUpdateStrategy(request);
            case DESSERT -> new DessertUpdateStrategy(request);
            case BEVERAGE -> new BeverageUpdateStrategy(request);
            case SANDWICH -> new SandwichUpdateStrategy(request);
            case SIDE_DISH -> new SideDishUpdateStrategy(request);
        };
    }
}
