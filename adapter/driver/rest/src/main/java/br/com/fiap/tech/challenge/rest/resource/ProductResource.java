package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.CreateProductService;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.resource.request.CreateProductRequest;
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

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductResource {

    private final ModelMapper mapper;
    private final FindAllAvailableProductService findAllAvailableProductService;
    private final FindProductByUUIDService findProductByUUIDService;
    private final CreateProductService createProductService;

    public ProductResource(@Qualifier("restModelMapper") ModelMapper mapper, FindAllAvailableProductService findAllAvailableProductService, FindProductByUUIDService findProductByUUIDService, CreateProductService createProductService) {
        this.mapper = mapper;
        this.findAllAvailableProductService = findAllAvailableProductService;
        this.findProductByUUIDService = findProductByUUIDService;
        this.createProductService = createProductService;
    }

    @GetMapping
    public ResponseList<ProductResponse> getAllAvailable(@ParameterObject Pageable pageable) {
        return ResponseList.from(
                findAllAvailableProductService.list(Pages.of(pageable)),
                e -> mapper.map(e, ProductResponse.class)
        );
    }

    @GetMapping("/{uuid}")
    public ProductResponse getByUUID(@PathVariable String uuid){
        return mapper.map(
                findProductByUUIDService.get(UUID.fromString(uuid)),
                ProductResponse.class
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody @Valid CreateProductRequest request){
        return mapper.map(
                createProductService.create(request.toDomain(mapper)),
                ProductResponse.class
        );
    }
}
