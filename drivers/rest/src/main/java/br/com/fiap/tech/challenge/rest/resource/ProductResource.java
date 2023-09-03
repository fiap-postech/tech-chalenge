package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.adapter.controller.product.CreateProductController;
import br.com.fiap.tech.challenge.adapter.dto.ComboDTO;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.rest.mapping.CreateProductMapper;
import br.com.fiap.tech.challenge.rest.mapping.ProductResponseMapper;
import br.com.fiap.tech.challenge.rest.resource.doc.ProductResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.CreateComboProductRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateSingleProductRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.rest.util.Mappings;
import br.com.fiap.tech.challenge.rest.util.Pages;
import br.com.fiap.tech.challenge.usecase.product.DisableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.EnableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductByCategoryUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindProductByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.product.UpdateProductUseCase;
import br.com.fiap.tech.challenge.util.ResponseList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.rest.util.Mappings.toProductResponse;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductResource implements ProductResourceDoc {

    private final CreateProductMapper createProductMapper;
    private final ProductResponseMapper productResponseMapper;

    private final FindAllAvailableProductUseCase findAllAvailableProductUseCase;
    private final FindAllAvailableProductByCategoryUseCase findAllAvailableProductByCategoryUseCase;
    private final FindProductByUUIDUseCase findProductByUUIDUseCase;
    private final CreateProductController createProductController;
    private final UpdateProductUseCase updateProductUseCase;
    private final EnableProductUseCase enableProductUseCase;
    private final DisableProductUseCase disableProductUseCase;


    @GetMapping
    public ResponseList<ProductResponse> getAllAvailable(@ParameterObject Pageable pageable,
                                                         @RequestParam(required = false) ProductCategory category) {

        var result = Optional.ofNullable(category)
                .map(c -> findAllAvailableProductByCategoryUseCase.list(c, Pages.of(pageable)))
                .orElseGet(() -> findAllAvailableProductUseCase.list(Pages.of(pageable)));

        return ResponseList.from(result, Mappings::toProductResponse);
    }

    @GetMapping("/{uuid}")
    public ProductResponse getByUUID(@PathVariable String uuid) {
        return toProductResponse(findProductByUUIDUseCase.get(UUID.fromString(uuid)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid CreateProductRequest request) {
        if (request instanceof CreateComboProductRequest comboRequest) {
            return productResponseMapper.toResponse(
                    (ComboDTO) createProductController.create(createProductMapper.fromRequest(comboRequest))
            );
        }

        return productResponseMapper.toResponse(
                createProductController.create(createProductMapper.fromRequest((CreateSingleProductRequest) request))
        );
    }

    @PutMapping
    public ProductResponse update(@RequestBody @Valid UpdateProductRequest request) {
        return toProductResponse(updateProductUseCase.update(request.toDomain()));
    }

    @PatchMapping("/{uuid}/enable")
    public ProductResponse enable(@PathVariable String uuid) {
        return toProductResponse(enableProductUseCase.enable(findProductByUUIDUseCase.get(UUID.fromString(uuid))));
    }

    @PatchMapping("/{uuid}/disable")
    public ProductResponse disable(@PathVariable String uuid) {
        return toProductResponse(disableProductUseCase.disable(findProductByUUIDUseCase.get(UUID.fromString(uuid))));
    }
}
