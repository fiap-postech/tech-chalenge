package br.com.fiap.tech.challenge.rest.common.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;


@ToString
@EqualsAndHashCode
public abstract class Response implements Serializable {

    @Serial
    private static final long serialVersionUID = -529940882058735178L;


}
