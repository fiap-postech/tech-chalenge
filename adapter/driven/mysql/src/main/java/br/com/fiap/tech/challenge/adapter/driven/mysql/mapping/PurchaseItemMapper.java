package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseItemEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.ProductEntityRepository;
import br.com.fiap.tech.challenge.domain.entity.*;
import br.com.fiap.tech.challenge.domain.valueobject.Discount;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
import br.com.fiap.tech.challenge.domain.valueobject.PurchaseItem;
import br.com.fiap.tech.challenge.domain.valueobject.Quantity;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.error.ApplicationError.PAYMENT_NOT_FOUND;
import static br.com.fiap.tech.challenge.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.*;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static java.util.Objects.isNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class PurchaseItemMapper {

    @Autowired
    protected ProductEntityRepository productRepository;

    @Mapping(target = "discount", source = "source", qualifiedByName = "getDiscountEntity")
    @Mapping(target = "price", source = "source", qualifiedByName = "getPriceEntity")
    @Mapping(target = "quantity", source = "source", qualifiedByName = "getQuantityEntity")
    @Mapping(target = "product", source = "source", qualifiedByName = "getProductEntity")
    public abstract PurchaseItemEntity toPurchaseItemEntity(PurchaseItem source);

    @Mapping(target = "discount", source = "source", qualifiedByName = "getDiscount")
    @Mapping(target = "price", source = "source.getPrice()", qualifiedByName = "getPrice")
    @Mapping(target = "fullPrice", source = "source.getFullPrice()", qualifiedByName = "getPrice")
    @Mapping(target = "quantity", source = "source", qualifiedByName = "getQuantity")
    @Mapping(target = "product", source = "source", qualifiedByName = "getProduct")
    public abstract PurchaseItem toPurchaseItem(PurchaseItemEntity source);


    @Named("getDiscountEntity")
    BigDecimal getDiscountEntity(PurchaseItem purchaseItem) {
        return discountToBigDecimalConverter(purchaseItem.discount());
    }

    @Named("getDiscount")
    Discount getDiscount(PurchaseItemEntity purchaseItemEntity) {
        return Discount.of(makeMoney(purchaseItemEntity.getDiscount()));
    }

    @Named("getPriceEntity")
    BigDecimal getPriceEntity(PurchaseItem purchaseItem) {
        return priceToBigDecimalConverter(purchaseItem.price());
    }

    @Named("getPrice")
    Price getPrice(BigDecimal price) {
        return Price.of(makeMoney(price));
    }

    @Named("getQuantityEntity")
    Integer getQuantityEntity(PurchaseItem purchaseItem) {
        return quantityToIntegerConverter(purchaseItem.quantity());
    }

    @Named("getQuantity")
    Quantity getQuantity(PurchaseItemEntity purchaseItemEntity) {
        return Quantity.of(purchaseItemEntity.getQuantity());
    }

    @Named("getProductEntity")
    ProductEntity getProductEntity(PurchaseItem purchaseItem) {
        var product = purchaseItem.product();
        if (isNull(product)) return null;
        var uuid = product.uuid().toString();

        return productRepository.findByUuid(uuid)
                .orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, uuid));
    }

    @Named("getProduct")
    Product getProduct(PurchaseItemEntity purchaseItemEntity) {
        var productEntity = purchaseItemEntity.getProduct();

        if(isNull(productEntity)) return null;

        return switch (productEntity.getCategory()){
            case BEVERAGE -> BeverageMapper.INSTANCE.toBeverage(productEntity);
            case SANDWICH -> SandwichMapper.INSTANCE.toSandwich(productEntity);
            case DESSERT -> DessertMapper.INSTANCE.toDessert(productEntity);
            case SIDE_DISH -> SideDishMapper.INSTANCE.toSideDish(productEntity);
            case COMBO -> ComboMapper.INSTANCE.toCombo(productEntity);
        };
    }
}
