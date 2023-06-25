package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.domain.validation.Document;
import br.com.fiap.tech.challenge.port.driver.CreateCustomerService;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByDocumentService;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@Validated
public class CustomerResource {

    private CreateCustomerService createCustomerService;
    private FindCustomerByDocumentService findCustomerByDocumentService;
    private ModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@RequestBody @Valid CreateCustomerRequest request){
        return mapper.map(
                createCustomerService.create(request.toDomain(mapper)),
                CustomerResponse.class
        );
    }

    @GetMapping("/{document}")
    public ResponseEntity<CustomerResponse> getByDocument(@PathVariable("document") @Document String document){
        return findCustomerByDocumentService.get(document)
                .map(customer -> ResponseEntity.ok(mapper.map(customer, CustomerResponse.class)))
                .orElse(ResponseEntity.ok().build());
    }
}
