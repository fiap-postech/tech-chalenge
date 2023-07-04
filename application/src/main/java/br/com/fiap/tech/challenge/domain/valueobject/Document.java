package br.com.fiap.tech.challenge.domain.valueobject;

import br.com.fiap.tech.challenge.domain.validation.DocumentCustomer;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;

@Accessors(fluent = true)
@Getter
public class Document extends ValueObject {
    @Serial
    private static final long serialVersionUID = -7027977210412190271L;

    @DocumentCustomer
    private final String document;

    private Document(String document) {
        this.document = document;

        validate();
    }

    public static Document of(String document) {
        return new Document(document);
    }
}
