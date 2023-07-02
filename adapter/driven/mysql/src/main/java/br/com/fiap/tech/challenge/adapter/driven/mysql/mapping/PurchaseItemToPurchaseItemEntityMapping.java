package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseItemEntity;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.PurchaseItem;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.quantityToIntegerConverter;
import static java.util.Objects.isNull;

@Mapper
public class PurchaseItemToPurchaseItemEntityMapping implements MySQLTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
       mapper.typeMap(PurchaseItem.class, PurchaseItemEntity.class)
               .addMappings(mapping -> mapping.using(discountToBigDecimalConverter()).map(PurchaseItem::discount, PurchaseItemEntity::setDiscount))
               .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(PurchaseItem::price, PurchaseItemEntity::setPrice))
               .addMappings(mapping -> mapping.using(quantityToIntegerConverter()).map(PurchaseItem::quantity, PurchaseItemEntity::setQuantity))
               .addMappings(mapping -> mapping.using(productConverter(mapper)).map(PurchaseItem::product, PurchaseItemEntity::setProduct));
    }

    private Converter<? extends Product, ProductEntity> productConverter(ModelMapper mapper) {
        return ctx -> {
            var product = ctx.getSource();

            if (isNull(product)){
                return null;
            }

            return switch (product.category()){
                case COMBO -> mapper.map(product, ComboEntity.class);
                case DESSERT, SANDWICH, BEVERAGE, SIDE_DISH -> mapper.map(product, ProductEntity.class);
            };
        };
    }
}
