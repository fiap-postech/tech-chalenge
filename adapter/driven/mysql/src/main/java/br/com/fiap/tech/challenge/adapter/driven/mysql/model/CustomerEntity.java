package br.com.fiap.tech.challenge.adapter.driven.mysql.model;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.domain.validation.Document;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
public class CustomerEntity extends JPAEntity{
    @Serial
    private static final long serialVersionUID = -6898703406360602814L;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @Document
    private String document;

    public Customer toDomain(){
        return Customer.builder()
                .name(getName())
                .email(getEmail())
                .document(getDocument())
                .build();
    }
}
