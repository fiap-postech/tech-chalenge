package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.domain.CartItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serial;
import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@RedisHash(value = "cart_item")
@Getter
@Setter
@ToString
public class CartItemEntity extends RedisEntity {

    @Serial
    private static final long serialVersionUID = 1112704544079814369L;

    @Indexed
    private String cartId;

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