package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.domain.Document;
import br.com.fiap.tech.challenge.domain.EmailRegistration;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerEntity {

    private String id;
    private String name;
    private Document document;
    private EmailRegistration email;
    private Boolean enabled;
}