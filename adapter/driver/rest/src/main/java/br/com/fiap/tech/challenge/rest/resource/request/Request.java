package br.com.fiap.tech.challenge.rest.resource.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;


@ToString
@EqualsAndHashCode
public abstract class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = -529940882058735178L;


}
