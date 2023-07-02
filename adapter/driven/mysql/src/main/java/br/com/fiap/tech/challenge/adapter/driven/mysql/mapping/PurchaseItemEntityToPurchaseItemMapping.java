package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseItemEntity;
import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Combo;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Discount;
import br.com.fiap.tech.challenge.domain.Price;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.PurchaseItem;
import br.com.fiap.tech.challenge.domain.Quantity;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public class PurchaseItemEntityToPurchaseItemMapping implements MySQLTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
       mapper.typeMap(PurchaseItemEntity.class, PurchaseItem.class)
               .setProvider(purchaseItemProvider(mapper));
    }

    private Provider<PurchaseItem> purchaseItemProvider(ModelMapper mapper) {
        return provision -> {
            var request = (PurchaseItemEntity) provision.getSource();

            return PurchaseItem.builder()
                    .quantity(Quantity.of(request.getQuantity()))
                    .price(Price.of(makeMoney(request.getPrice())))
                    .discount(Discount.of(makeMoney(request.getDiscount())))
                    .fullPrice(Price.of(makeMoney(request.getFullPrice())))
                    .product(toProduct(request.getProduct(), mapper))
                    .build();
        };
    }

    private Product toProduct(ProductEntity entity, ModelMapper mapper) {
        return switch (entity.getCategory()){
            case COMBO -> mapper.map(entity, Combo.class);
            case SANDWICH -> mapper.map(entity, Sandwich.class);
            case SIDE_DISH -> mapper.map(entity, SideDish.class);
            case BEVERAGE -> mapper.map(entity, Beverage.class);
            case DESSERT -> mapper.map(entity, Dessert.class);
        };
    }
}
