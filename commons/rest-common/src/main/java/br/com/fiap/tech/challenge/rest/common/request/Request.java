package br.com.fiap.tech.challenge.rest.common.request;

import br.com.fiap.tech.challenge.AssertionConcern;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;


@ToString
@EqualsAndHashCode
public abstract class Request<T extends AssertionConcern> implements Serializable {

    @Serial
    private static final long serialVersionUID = -529940882058735178L;

    public abstract T toDomain();

}
