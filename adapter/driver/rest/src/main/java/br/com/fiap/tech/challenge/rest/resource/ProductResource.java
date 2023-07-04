package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.domain.enums.ProductCategory;
import br.com.fiap.tech.challenge.port.driver.*;
import br.com.fiap.tech.challenge.rest.resource.doc.ProductResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.CreateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.rest.util.Pages;
import br.com.fiap.tech.challenge.util.ResponseList;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.rest.config.RestModelMapperConfiguration.REST_MODEL_MAPPER;
import static br.com.fiap.tech.challenge.rest.util.Mappings.toProductResponse;
@RestController
@RequestMapping("/product")
public class ProductResource implements ProductResourceDoc {

    private final ModelMapper mapper;
    private final FindAllAvailableProductService findAllAvailableProductService;
    private final FindAllAvailableProductByCategory findAllAvailableProductByCategory;
    private final FindProductByUUIDService findProductByUUIDService;
    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;
    private final EnableProductService enableProductService;
    private final DisableProductService disableProductService;

    @SuppressWarnings("squid:S107")
    public ProductResource(@Qualifier(REST_MODEL_MAPPER) ModelMapper mapper,
                           FindAllAvailableProductService findAllAvailableProductService,
                           FindAllAvailableProductByCategory findAllAvailableProductByCategory,
                           FindProductByUUIDService findProductByUUIDService,
                           CreateProductService createProductService,
                           UpdateProductService updateProductService,
                           EnableProductService enableProductService,
                           DisableProductService disableProductService) {
        this.mapper = mapper;
        this.findAllAvailableProductService = findAllAvailableProductService;
        this.findAllAvailableProductByCategory = findAllAvailableProductByCategory;
        this.findProductByUUIDService = findProductByUUIDService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.enableProductService = enableProductService;
        this.disableProductService = disableProductService;
    }

    @GetMapping
    public ResponseList<ProductResponse> getAllAvailable(@ParameterObject Pageable pageable,
                                                         @RequestParam(required = false) ProductCategory category) {

        var result = Optional.ofNullable(category)
                .map(c -> findAllAvailableProductByCategory.list(c, Pages.of(pageable)))
                .orElseGet(() -> findAllAvailableProductService.list(Pages.of(pageable)));

        return ResponseList.from(result, e -> toProductResponse(mapper, e));
    }

    @GetMapping("/{uuid}")
    public ProductResponse getByUUID(@PathVariable String uuid) {
        return toProductResponse(
                mapper,
                findProductByUUIDService.get(UUID.fromString(uuid))
        );
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
