package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.FindPurchaseByPaymentIdUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.UpdatePurchaseUseCase;
import br.com.fiap.tech.challenge.rest.mapping.PurchaseMapperRest;
import br.com.fiap.tech.challenge.rest.resource.doc.PurchaseResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.PaymentConfirmRequest;
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
    private final FindAllPurchasesUseCase findAllPurchasesUseCase;
    private final FindPurchaseByUUIDUseCase findPurchaseByUUIDUseCase;
    private final FindPurchaseByPaymentIdUseCase findPurchaseByPaymentIdUseCase;
    private final UpdatePurchaseUseCase updatePurchaseUseCase;

    public PurchaseResource(
            PurchaseMapperRest purchaseMapperRest,
            FindAllPurchasesUseCase findAllPurchasesUseCase,
            FindPurchaseByUUIDUseCase findPurchaseByUUIDUseCase,
            FindPurchaseByPaymentIdUseCase findPurchaseByPaymentIdUseCase,
            UpdatePurchaseUseCase updatePurchaseUseCase) {
        this.purchaseMapperRest = purchaseMapperRest;
        this.findAllPurchasesUseCase = findAllPurchasesUseCase;
        this.findPurchaseByUUIDUseCase = findPurchaseByUUIDUseCase;
        this.findPurchaseByPaymentIdUseCase = findPurchaseByPaymentIdUseCase;
        this.updatePurchaseUseCase = updatePurchaseUseCase;
    }

    @GetMapping
    public ResponseList<PurchseResponse> getAllAvailable(@ParameterObject Pageable pageable) {
        return ResponseList.from(
                findAllPurchasesUseCase.list(Pages.of(pageable)),
                purchaseMapperRest::toPurchseResponse
        );
    }

    @GetMapping("/{uuid}")
    public PurchseResponse getByUUID(@PathVariable String uuid) {
        return purchaseMapperRest.toPurchseResponse(findPurchaseByUUIDUseCase.get(UUID.fromString(uuid)));
    }

    @PatchMapping("/{uuid}/{status}")
    public PurchseResponse updatePurchaseStatus(@PathVariable String uuid, @PathVariable PurchaseStatus status) {
        var purchase = findPurchaseByUUIDUseCase.get(UUID.fromString(uuid));
        return purchaseMapperRest.toPurchseResponse(updatePurchaseUseCase.updateStatus(purchase, status));
    }

    @PostMapping("/confirm")
    public PurchseResponse confirmPaymentForPurchase(@RequestBody PaymentConfirmRequest confirmRequest) {
        var purchase = findPurchaseByPaymentIdUseCase.getPurchase(confirmRequest.paymentId());
        return purchaseMapperRest.toPurchseResponse(updatePurchaseUseCase.updateStatus(purchase, PurchaseStatus.PAID));
    }
}
