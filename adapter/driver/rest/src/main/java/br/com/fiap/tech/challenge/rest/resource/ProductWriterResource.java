package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.CreateProductService;
import br.com.fiap.tech.challenge.port.driver.DisableProductService;
import br.com.fiap.tech.challenge.port.driver.EnableProductService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.port.driver.UpdateProductService;
import br.com.fiap.tech.challenge.rest.resource.request.CreateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static br.com.fiap.tech.challenge.rest.config.RestModelMapperConfiguration.REST_MODEL_MAPPER;
import static br.com.fiap.tech.challenge.rest.util.Mappings.toProductResponse;

@RestController
@RequestMapping("/product")
public class ProductWriterResource {

    private final ModelMapper mapper;
    private final FindProductByUUIDService findProductByUUIDService;
    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;
    private final EnableProductService enableProductService;
    private final DisableProductService disableProductService;

    public ProductWriterResource(@Qualifier(REST_MODEL_MAPPER) ModelMapper mapper,
                                 FindProductByUUIDService findProductByUUIDService,
                                 CreateProductService createProductService,
                                 UpdateProductService updateProductService,
                                 EnableProductService enableProductService,
                                 DisableProductService disableProductService) {
        this.mapper = mapper;
        this.findProductByUUIDService = findProductByUUIDService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.enableProductService = enableProductService;
        this.disableProductService = disableProductService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid CreateProductRequest request) {
        return toProductResponse(
                mapper,
                createProductService.create(request.toDomain(mapper))
        );
    }

    @PutMapping
    public ProductResponse update(@RequestBody @Valid UpdateProductRequest request) {
        return toProductResponse(
                mapper,
                updateProductService.update(request.toDomain(mapper))
        );
    }

    @PatchMapping("/{uuid}/enable")
    public ProductResponse enable(@PathVariable String uuid) {
        return toProductResponse(
                mapper,
                enableProductService.enable(findProductByUUIDService.get(UUID.fromString(uuid)))
        );
    }

    @PatchMapping("/{uuid}/disable")
    public ProductResponse disable(@PathVariable String uuid) {
        return toProductResponse(
                mapper,
                disableProductService.disable(findProductByUUIDService.get(UUID.fromString(uuid)))
        );
    }
}
