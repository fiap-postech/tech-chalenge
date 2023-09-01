package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.enterprise.validation.DocumentCustomer;
import br.com.fiap.tech.challenge.port.driver.CreateCustomerService;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByDocumentService;
import br.com.fiap.tech.challenge.port.driver.UpgradeCustomerService;
import br.com.fiap.tech.challenge.rest.mapping.CustomerMapperRest;
import br.com.fiap.tech.challenge.rest.resource.doc.CustomerResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerResource implements CustomerResourceDoc {

    private final CreateCustomerService createCustomerService;
    private final FindCustomerByDocumentService findCustomerByDocumentService;
    private final CustomerMapperRest mapper;
    private final UpgradeCustomerService upgradeCustomerService;

    public CustomerResource(
            CustomerMapperRest mapper,
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
    public CustomerResponse create(@RequestBody @Valid CreateCustomerRequest request) {
        return mapper.toCustomerResponse(createCustomerService.create(request.toDomain()));
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> getByDocument(@RequestParam("document") @DocumentCustomer String document) {
        return findCustomerByDocumentService.get(document)
                .map(customer -> ResponseEntity.ok(mapper.toCustomerResponse(customer)))
                .orElse(ResponseEntity.noContent().build());
    }

    @PatchMapping("/{document}/disable")
    public ResponseEntity<CustomerResponse> disable(@PathVariable("document") @DocumentCustomer String document) {
        return upgradeCustomerService.disable(document)
                .map(customer -> ResponseEntity.ok(mapper.toCustomerResponse(customer)))
                .orElse(ResponseEntity.noContent().build());
    }
}
