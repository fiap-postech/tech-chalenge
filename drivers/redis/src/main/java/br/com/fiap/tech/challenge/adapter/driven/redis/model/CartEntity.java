package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.CartItemMapper;
import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.CustomerMapper;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@RedisHash(value = "cart")
@Getter
@Setter
@ToString
public class CartEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1085132067646799595L;

    @Id
    private String id;

    private CustomerEntity customer;

    private List<CartItemEntity> items;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long ttl;

    public Cart toDomain(CartItemMapper cartItemMapper, CustomerMapper customerMapper) {
        var builder = Cart.builder();

        if (!isEmpty(this.getItems())) {
            builder.items(this.getItems().stream()
                    .map(c -> c.toDomain(cartItemMapper))
                    .toList());
        }

        if (nonNull(this.customer)) {
            builder.customer(customerMapper.toCustomer(this.customer));
        }

        return builder
                .uuid(UUID.fromString(this.getId()))
                .build();
    }
}