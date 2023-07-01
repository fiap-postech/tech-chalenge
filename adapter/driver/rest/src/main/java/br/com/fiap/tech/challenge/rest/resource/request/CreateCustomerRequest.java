package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.domain.validation.DocumentCustomer;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateCustomerRequest extends Request<Customer> {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @DocumentCustomer
    private String document;

    @Override
    public Customer toDomain(ModelMapper mapper) {
        return mapper.map(this, Customer.class);
    }
}
