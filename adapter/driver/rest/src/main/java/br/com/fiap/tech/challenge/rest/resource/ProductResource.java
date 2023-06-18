package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.CreateProductService;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.resource.request.CreateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.rest.util.Pages;
import br.com.fiap.tech.challenge.util.ResponseList;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductResource {

    private ModelMapper mapper;
    private FindAllAvailableProductService findAllAvailableProductService;
    private FindProductByUUIDService findProductByUUIDService;
    private CreateProductService createProductService;

    @GetMapping
    public ResponseList<ProductResponse> getAllAvailable(@ParameterObject Pageable pageable) {
        return ResponseList.from(
                findAllAvailableProductService.list(Pages.of(pageable)),
                e -> mapper.map(e, ProductResponse.class)
        );
    }

    @GetMapping("/{uuid}")
    public ProductResponse getByUUID(@PathVariable String uuid){
        //TODO create exception handler to generate custom errors
        return mapper.map(
                findProductByUUIDService.get(UUID.fromString(uuid)),
                ProductResponse.class
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid CreateProductRequest request){
        return mapper.map(
                createProductService.create(request.toDomain(mapper)),
                ProductResponse.class
        );
    }
}
