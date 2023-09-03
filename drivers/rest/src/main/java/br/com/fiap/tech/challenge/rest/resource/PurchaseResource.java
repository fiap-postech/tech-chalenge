package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.port.driver.FindAllPurchasesService;
import br.com.fiap.tech.challenge.port.driver.FindPurchaseByUUIDService;
import br.com.fiap.tech.challenge.port.driver.UpdatePurchaseService;
import br.com.fiap.tech.challenge.rest.mapping.PurchaseMapperRest;
import br.com.fiap.tech.challenge.rest.resource.doc.PurchaseResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.response.PurchseResponse;
import br.com.fiap.tech.challenge.rest.util.Pages;
import br.com.fiap.tech.challenge.util.ResponseList;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseResource implements PurchaseResourceDoc {

    private final PurchaseMapperRest purchaseMapperRest;
    private final FindAllPurchasesService findAllPurchasesService;
    private final FindPurchaseByUUIDService findPurchaseByUUIDService;
    private final UpdatePurchaseService updatePurchaseService;

    public PurchaseResource(
            PurchaseMapperRest purchaseMapperRest,
            FindAllPurchasesService findAllPurchasesService,
            FindPurchaseByUUIDService findPurchaseByUUIDService,
            UpdatePurchaseService updatePurchaseService) {
        this.purchaseMapperRest = purchaseMapperRest;
        this.findAllPurchasesService = findAllPurchasesService;
        this.findPurchaseByUUIDService = findPurchaseByUUIDService;
        this.updatePurchaseService = updatePurchaseService;
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

    @PatchMapping("/{uuid}/{status}")
    public PurchseResponse updatePurchaseStatus(@PathVariable String uuid, @PathVariable PurchaseStatus status) {
        var purchase = findPurchaseByUUIDService.get(UUID.fromString(uuid));
        return purchaseMapperRest.toPurchseResponse(updatePurchaseService.updateStatus(purchase, status));
    }
}
