package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.domain.CartItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@RedisHash(value = "cart_item")
@Getter
@Setter
@ToString
public class CartItemEntity {

    private ProductEntity product;
    private BigDecimal price;
    private BigDecimal discount;
    private int quantity;

    public CartItem toDomain(ModelMapper mapper) {
        var cartItem = mapper.map(this, CartItem.class);

        var builder = cartItem.toBuilder();
        if (nonNull(this.getProduct())) {
            builder.product(this.getProduct().toDomain(mapper));
        }

        return builder.build();
    }
}