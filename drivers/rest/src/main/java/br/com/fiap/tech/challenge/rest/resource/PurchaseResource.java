package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.adapter.controller.purchase.FindAllPurchasesController;
import br.com.fiap.tech.challenge.adapter.controller.purchase.FindPurchaseByUUIDController;
import br.com.fiap.tech.challenge.adapter.controller.purchase.PaymentConfirmController;
import br.com.fiap.tech.challenge.adapter.controller.purchase.UpdatePurchaseStatusController;
import br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.rest.mapping.PaymentConfirmMapper;
import br.com.fiap.tech.challenge.rest.mapping.PurchaseResponseMapper;
import br.com.fiap.tech.challenge.rest.resource.doc.PurchaseResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.PaymentConfirmRequest;
import br.com.fiap.tech.challenge.rest.resource.response.PurchseResponse;
import br.com.fiap.tech.challenge.rest.util.Pages;
import br.com.fiap.tech.challenge.application.util.ResponseList;
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

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseResource implements PurchaseResourceDoc {

    private final PaymentConfirmMapper paymentConfirmMapper;
    private final PurchaseResponseMapper purchaseResponseMapper;

    private final FindAllPurchasesController findAllPurchasesController;
    private final FindPurchaseByUUIDController findPurchaseByUUIDController;
    private final UpdatePurchaseStatusController updatePurchaseStatusController;
    private final PaymentConfirmController paymentConfirmController;

    @GetMapping
    public ResponseList<PurchseResponse> getAllAvailable(@ParameterObject Pageable pageable) {
        return ResponseList.from(
                findAllPurchasesController.list(Pages.of(pageable)),
                purchaseResponseMapper::toResponse
        );
    }

    @GetMapping("/{uuid}")
    public PurchseResponse getByUUID(@PathVariable String uuid) {
        return purchaseResponseMapper.toResponse(findPurchaseByUUIDController.get(uuid));
    }

    @PatchMapping("/{uuid}/{status}")
    public PurchseResponse updatePurchaseStatus(@PathVariable String uuid, @PathVariable PurchaseStatus status) {
        return purchaseResponseMapper.toResponse(updatePurchaseStatusController.update(uuid, status));
    }

    @PostMapping("/confirm")
    public PurchseResponse confirmPaymentForPurchase(@RequestBody PaymentConfirmRequest confirmRequest) {
        return purchaseResponseMapper.toResponse(
                paymentConfirmController.confirm(paymentConfirmMapper.toDTO(confirmRequest))
        );
    }
}
