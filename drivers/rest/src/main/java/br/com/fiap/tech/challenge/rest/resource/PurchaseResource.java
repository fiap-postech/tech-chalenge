package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.adapter.controller.purchase.PaymentConfirmController;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.rest.mapping.PaymentConfirmMapper;
import br.com.fiap.tech.challenge.rest.mapping.PurchaseMapperRest;
import br.com.fiap.tech.challenge.rest.mapping.PurchaseResponseMapper;
import br.com.fiap.tech.challenge.rest.resource.doc.PurchaseResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.PaymentConfirmRequest;
import br.com.fiap.tech.challenge.rest.resource.response.PurchseResponse;
import br.com.fiap.tech.challenge.rest.util.Pages;
import br.com.fiap.tech.challenge.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.FindPurchaseByPaymentIdUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.UpdatePurchaseUseCase;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseResource implements PurchaseResourceDoc {

    private final PaymentConfirmMapper paymentConfirmMapper;
    private final PurchaseResponseMapper purchaseResponseMapper;

    private final PurchaseMapperRest purchaseMapperRest;
    private final FindAllPurchasesUseCase findAllPurchasesUseCase;
    private final FindPurchaseByUUIDUseCase findPurchaseByUUIDUseCase;
    private final FindPurchaseByPaymentIdUseCase findPurchaseByPaymentIdUseCase;
    private final UpdatePurchaseUseCase updatePurchaseUseCase;
    private final PaymentConfirmController paymentConfirmController;

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
        return purchaseResponseMapper.toResponse(
                paymentConfirmController.confirm(paymentConfirmMapper.toDTO(confirmRequest))
        );
    }
}
