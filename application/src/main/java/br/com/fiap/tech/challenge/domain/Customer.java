package br.com.fiap.tech.challenge.domain;

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

    @Serial
    private static final long serialVersionUID = 213659655671060163L;

    @NotBlank
    private final String name;

    private final EmailRegistration email;

    private final Document document;

    private boolean enabled;

    @Builder
    public Customer(UUID uuid, String name, EmailRegistration email, Document document, boolean enabled) {
        super(uuid);

        this.name = name;
        this.email = email;
        this.document = document;
        this.enabled = enabled;

        validate();
    }

    public String toDocument() {
        return document.document();
    }

    public String toEmail() {
        return email.email();
    }
}
