package br.com.fiap.tech.challenge.rest.dto;

import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@ToString
public abstract class DTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -529940882058735178L;


}
