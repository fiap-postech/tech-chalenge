package br.com.fiap.tech.challenge.domain;

import br.com.fiap.tech.challenge.domain.validation.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;
@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class Customer extends Entity{
    //TODO: verificar mantemos email e document como String e validamos, ou deveriam ser VO? Qual o ganho deles como VO?

    @Serial
    private static final long serialVersionUID = 213659655671060163L;

    @NotBlank
    private final String name;

    @NotBlank
    @Email
    private final String email;

    @Document
    private final String document;

    @Builder
    public Customer(UUID uuid, String name, String email, String document) {
        super(uuid);

        this.name = name;
        this.email = email;
        this.document = document;

        validate();
    }
}
