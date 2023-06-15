package br.com.fiap.tech.challenge.domain;

import lombok.Getter;

import java.io.Serial;
import java.util.UUID;

@Getter
public class Cart extends Entity {
    @Serial
    private static final long serialVersionUID = -7454461774303685197L;

    public Cart(UUID uuid) {
        super(uuid);
    }

    public Cart addItem(){
        //add item

        //DomainPublishers().post(ItemAdicionado)


        return this;
    }
}
