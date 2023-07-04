package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.FindAllPurchasesService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByUUIDService;
import br.com.fiap.tech.challenge.rest.resource.doc.PurchaseResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.response.PurchseResponse;
import br.com.fiap.tech.challenge.rest.util.Pages;
import br.com.fiap.tech.challenge.util.ResponseList;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static br.com.fiap.tech.challenge.rest.config.RestModelMapperConfiguration.REST_MODEL_MAPPER;

@RestController
@RequestMapping("/purchase")
public class PurchaseResource implements PurchaseResourceDoc {

    private final ModelMapper mapper;
    private final FindAllPurchasesService findAllPurchasesService;
    private final FindPurchaseByUUIDService findPurchaseByUUIDService;

    public PurchaseResource(@Qualifier(REST_MODEL_MAPPER) ModelMapper mapper,
                            FindAllPurchasesService findAllPurchasesService,
                            FindPurchaseByUUIDService findPurchaseByUUIDService) {
        this.mapper = mapper;
        this.findAllPurchasesService = findAllPurchasesService;
        this.findPurchaseByUUIDService = findPurchaseByUUIDService;
    }

    @GetMapping
    public ResponseList<PurchseResponse> getAllAvailable(@ParameterObject Pageable pageable) {
        return ResponseList.from(
                findAllPurchasesService.list(Pages.of(pageable)),
                e -> mapper.map(e, PurchseResponse.class)
        );
    }

    @GetMapping("/{uuid}")
    public PurchseResponse getByUUID(@PathVariable String uuid) {
        return mapper.map(
                findPurchaseByUUIDService.get(UUID.fromString(uuid)),
                PurchseResponse.class
        );
    }
}
