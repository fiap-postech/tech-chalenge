package br.com.fiap.tech.challenge.rest.resource.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AddCartItemProductRequest extends ProductRequest {

    @Serial
    private static final long serialVersionUID = -5302857914829450023L;

    @NotEmpty
    private String id;
}