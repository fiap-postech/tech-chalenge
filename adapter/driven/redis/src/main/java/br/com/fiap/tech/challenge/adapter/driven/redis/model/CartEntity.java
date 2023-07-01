package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
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

    public Cart toDomain(ModelMapper mapper) {
        var builder = Cart.builder();

        if (!isEmpty(this.getItems())) {
            builder.items(this.getItems().stream()
                    .map(c -> c.toDomain(mapper))
                    .toList());
        }

        if (nonNull(this.customer)) {
            builder.customer(mapper.map(this.customer, Customer.class));
        }

        return builder
                .uuid(UUID.fromString(this.getId()))
                .build();
    }
}