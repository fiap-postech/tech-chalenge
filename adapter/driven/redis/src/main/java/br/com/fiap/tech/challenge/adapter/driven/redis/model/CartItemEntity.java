package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.CartItemMapper;
import br.com.fiap.tech.challenge.domain.entity.CartItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Getter
@Setter
@ToString
public class CartItemEntity {

    @Autowired
    private CartItemMapper cartItemMapper;

    private ProductEntity product;
    private BigDecimal price;
    private BigDecimal discount;
    private int quantity;

    public CartItem toDomain(ModelMapper mapper) {
        var cartItem = cartItemMapper.toCartItemEntity(this);

        var builder = cartItem.toBuilder();
        if (nonNull(this.getProduct())) {
            builder.product(this.getProduct().toDomain());
        }

        return builder.build();
    }
}