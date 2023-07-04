package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.domain.valueobject.PurchaseItem;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.rest.resource.response.PurchaseItemResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.quantityToIntegerConverter;
import static br.com.fiap.tech.challenge.rest.util.Mappings.toProductResponse;

@Mapper
@RequiredArgsConstructor
public class PurchaseItemToPurchaseItemResponseMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(PurchaseItem.class, PurchaseItemResponse.class)
                .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(PurchaseItem::price, PurchaseItemResponse::setPrice))
                .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(PurchaseItem::fullPrice, PurchaseItemResponse::setFullPrice))
                .addMappings(mapping -> mapping.using(discountToBigDecimalConverter()).map(PurchaseItem::discount, PurchaseItemResponse::setDiscount))
                .addMappings(mapping -> mapping.using(quantityToIntegerConverter()).map(PurchaseItem::quantity, PurchaseItemResponse::setQuantity))
                .addMappings(mapping -> mapping.using(productConverter(mapper)).map(PurchaseItem::product, PurchaseItemResponse::setProduct));
    }

    private Converter<Product, ProductResponse> productConverter(ModelMapper mapper){
        return ctx -> toProductResponse(mapper, ctx.getSource());
    }
}
