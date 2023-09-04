package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.CartItemMapper;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Getter
@Setter
@ToString
public class CartItemEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -304755728066875717L;

    private ProductEntity product;
    private BigDecimal price;
    private BigDecimal discount;
    private int quantity;

    public CartItem toDomain(CartItemMapper cartItemMapper) {
        var cartItem = cartItemMapper.toCartItemEntity(this);

        var builder = cartItem.toBuilder();
        if (nonNull(this.getProduct())) {
            builder.product(this.getProduct().toDomain());
        }

        return builder.build();
    }
}