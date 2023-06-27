package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.domain.Cart;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@RedisHash(value = "cart")
@Getter
@Setter
@ToString
public class CartEntity extends RedisEntity {

    @Serial
    private static final long serialVersionUID = -1085132067646799595L;

    private List<CartItemEntity> items;

    public Cart toDomain(ModelMapper mapper) {
        var builder = Cart.builder();

        if (!isEmpty(this.getItems())) {
            builder.items(this.getItems().stream()
                    .map(c -> c.toDomain(mapper))
                    .collect(Collectors.toSet()));
        }

        return builder
                .uuid(UUID.fromString(this.getId()))
                .build();
    }
}