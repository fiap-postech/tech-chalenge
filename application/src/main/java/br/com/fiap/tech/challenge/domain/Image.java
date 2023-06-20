package br.com.fiap.tech.challenge.domain;

import br.com.fiap.tech.challenge.domain.validation.URL;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Accessors(fluent = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Image extends ValueObject {
    @Serial
    private static final long serialVersionUID = -7027977210412190271L;

    @URL
    private final String url;

    public java.net.URL toURL() {
        try {
            return new java.net.URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e); //TODO change to specialized exception
        }
    }

    public URI toURI() {
        try {
            return toURL().toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e); //TODO change to specialized exception
        }
    }

    public static Image of(String url) {
        return new Image(url);
    }
}
