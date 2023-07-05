package br.com.fiap.tech.challenge.adapter.driven.mysql.util;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {

    public static ProductEntity toProductEntity(ModelMapper mapper, Product product){
        return switch (product.category()){
            case COMBO -> mapper.map(product, ComboEntity.class);
            case DESSERT, SANDWICH, BEVERAGE, SIDE_DISH -> mapper.map(product, ProductEntity.class);
        };
    }
}
