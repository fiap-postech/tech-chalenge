package br.com.fiap.tech.challenge.domain;

import br.com.fiap.tech.challenge.AssertionConcern;

import java.io.Serial;
import java.util.UUID;

import static java.util.Objects.isNull;

public abstract class Entity extends AssertionConcern {
    @Serial
    private static final long serialVersionUID = -4512991976943294994L;

    private UUID uuid;

    protected Entity(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID uuid(){
        this.ensureUUID();

        return uuid;
    }

    protected void ensureUUID(){
        if (isNull(uuid)){
            uuid = UUID.randomUUID();
        }
    }

}
