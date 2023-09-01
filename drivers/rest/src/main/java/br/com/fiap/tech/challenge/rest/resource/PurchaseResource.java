package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.FindAllPurchasesService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByUUIDService;
import br.com.fiap.tech.challenge.rest.mapping.PurchaseMapperRest;
import br.com.fiap.tech.challenge.rest.resource.doc.PurchaseResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.response.PurchseResponse;
import br.com.fiap.tech.challenge.rest.util.Pages;
import br.com.fiap.tech.challenge.util.ResponseList;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseResource implements PurchaseResourceDoc {

    private final PurchaseMapperRest purchaseMapperRest;
    private final FindAllPurchasesService findAllPurchasesService;
    private final FindPurchaseByUUIDService findPurchaseByUUIDService;

    public PurchaseResource(PurchaseMapperRest purchaseMapperRest,
                            FindAllPurchasesService findAllPurchasesService,
                            FindPurchaseByUUIDService findPurchaseByUUIDService) {
        this.purchaseMapperRest = purchaseMapperRest;
        this.findAllPurchasesService = findAllPurchasesService;
        this.findPurchaseByUUIDService = findPurchaseByUUIDService;
    }

    @GetMapping
    public ResponseList<PurchseResponse> getAllAvailable(@ParameterObject Pageable pageable) {
        return ResponseList.from(
                findAllPurchasesService.list(Pages.of(pageable)),
                purchaseMapperRest::toPurchseResponse
        );
    }

    @GetMapping("/{uuid}")
    public PurchseResponse getByUUID(@PathVariable String uuid) {
        return purchaseMapperRest.toPurchseResponse(findPurchaseByUUIDService.get(UUID.fromString(uuid)));
    }
}
