package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class Cart extends Entity {
    @Serial
    private static final long serialVersionUID = -7454461774303685197L;

    @Builder(toBuilder = true)
    public Cart(@Builder.ObtainVia(method = "uuid") UUID uuid) {
        super(uuid);
    }

    public Cart addItem(){
        //add item

        //DomainPublishers().post(ItemAdicionado)


        return this;
    }
}
