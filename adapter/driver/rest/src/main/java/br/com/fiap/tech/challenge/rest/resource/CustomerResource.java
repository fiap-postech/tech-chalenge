package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.domain.validation.DocumentCustomer;
import br.com.fiap.tech.challenge.port.driver.CreateCustomerService;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByDocumentService;
import br.com.fiap.tech.challenge.port.driver.UpgradeCustomerService;
import br.com.fiap.tech.challenge.rest.resource.doc.CustomerResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.tech.challenge.rest.config.RestModelMapperConfiguration.REST_MODEL_MAPPER;
@Tag(name = "CustomerResource", description = "API responsável pelo CRUD de Cliente")
@RestController
@RequestMapping("/customer")
@Validated
public class CustomerResource implements CustomerResourceDoc {

    private final CreateCustomerService createCustomerService;
    private final FindCustomerByDocumentService findCustomerByDocumentService;
    private final ModelMapper mapper;
    private final UpgradeCustomerService upgradeCustomerService;

    public CustomerResource(
            @Qualifier(REST_MODEL_MAPPER) ModelMapper mapper,
            CreateCustomerService createCustomerService,
            FindCustomerByDocumentService findCustomerByDocumentService,
            UpgradeCustomerService upgradeCustomerService
    ) {
        this.createCustomerService = createCustomerService;
        this.findCustomerByDocumentService = findCustomerByDocumentService;
        this.mapper = mapper;
        this.upgradeCustomerService = upgradeCustomerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@RequestBody @Valid CreateCustomerRequest request){
        return mapper.map(
                createCustomerService.create(request.toDomain(mapper)),
                CustomerResponse.class
        );
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> getByDocument(@RequestParam("document") @DocumentCustomer String document){
        return findCustomerByDocumentService.get(document)
                .map(customer -> ResponseEntity.ok(mapper.map(customer, CustomerResponse.class)))
                .orElse(ResponseEntity.noContent().build());
    }

    @PatchMapping("/{document}/disable")
    public ResponseEntity<CustomerResponse> disable(@PathVariable("document") @DocumentCustomer String document){
        return upgradeCustomerService.disable(document)
                .map(customer -> ResponseEntity.ok(mapper.map(customer, CustomerResponse.class)))
                .orElse(ResponseEntity.noContent().build());
    }
}
