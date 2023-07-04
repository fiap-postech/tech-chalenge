package br.com.fiap.tech.challenge.adapter.driven.mysql.model;

import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.domain.valueobject.Document;
import br.com.fiap.tech.challenge.domain.valueobject.EmailRegistration;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.UUID;

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
    @Column(columnDefinition = "text")
    private String email;

    @NotBlank
    @Column(columnDefinition = "text")
    private String document;

    private boolean enabled;

    public Customer toDomain(){
        return Customer.builder()
                .uuid(UUID.fromString(getUuid()))
                .name(getName())
                .email(EmailRegistration.of(getEmail()))
                .document(Document.of(getDocument()))
                .enabled(isEnabled())
                .build();
    }
}
