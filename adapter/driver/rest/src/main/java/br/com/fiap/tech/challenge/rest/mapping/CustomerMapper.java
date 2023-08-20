package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.domain.valueobject.Document;
import br.com.fiap.tech.challenge.domain.valueobject.EmailRegistration;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper {

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "email", expression = "java(source.toEmail())")
    @Mapping(target = "document", expression = "java(source.toDocument())")
    CustomerResponse toCustomerResponse(Customer source);

    @Mapping(target = "email", source = "source", qualifiedByName = "getEmail")
    @Mapping(target = "document", source = "source", qualifiedByName = "getDocument")
    @Mapping(target = "enabled", expression = "java(Boolean.TRUE)")
    Customer toCustomer(CreateCustomerRequest source);


    @Named("getEmail")
    static EmailRegistration getEmail(CreateCustomerRequest source){
        return EmailRegistration.of(source.getEmail());
    }

    @Named("getDocument")
    static Document getDocument(CreateCustomerRequest source){
        return Document.of(source.getDocument());
    }

}
