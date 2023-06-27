package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.domain.ProductCategory;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductByCategory;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.rest.util.Pages;
import br.com.fiap.tech.challenge.util.ResponseList;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.rest.config.RestModelMapperConfiguration.REST_MODEL_MAPPER;
import static br.com.fiap.tech.challenge.rest.util.Mappings.toProductResponse;

@RestController
@RequestMapping("/product")
public class ProductReaderResource {

    private final ModelMapper mapper;
    private final FindAllAvailableProductService findAllAvailableProductService;
    private final FindAllAvailableProductByCategory findAllAvailableProductByCategory;
    private final FindProductByUUIDService findProductByUUIDService;

    public ProductReaderResource(@Qualifier(REST_MODEL_MAPPER) ModelMapper mapper,
                                 FindAllAvailableProductService findAllAvailableProductService,
                                 FindAllAvailableProductByCategory findAllAvailableProductByCategory,
                                 FindProductByUUIDService findProductByUUIDService) {
        this.mapper = mapper;
        this.findAllAvailableProductService = findAllAvailableProductService;
        this.findAllAvailableProductByCategory = findAllAvailableProductByCategory;
        this.findProductByUUIDService = findProductByUUIDService;
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
}
