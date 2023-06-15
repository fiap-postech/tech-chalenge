package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class Order extends Entity {

    @Serial
    private static final long serialVersionUID = -9196907733871633595L;

    private OrderStatus status;


    public Order(UUID uuid) {
        super(uuid);

        status = OrderStatus.PAID;
    }
}
