package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.AssertionConcern;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.io.Serial;
import java.io.Serializable;


@ToString
@EqualsAndHashCode
public abstract class Request<T extends AssertionConcern> implements Serializable {

    @Serial
    private static final long serialVersionUID = -529940882058735178L;

    public abstract T toDomain(ModelMapper mapper);

}
