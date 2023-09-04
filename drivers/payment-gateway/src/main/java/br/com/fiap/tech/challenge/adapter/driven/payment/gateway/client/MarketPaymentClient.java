package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.client;

import br.com.fiap.tech.challenge.adapter.driven.payment.gateway.request.PaymentCheckoutRequest;
import br.com.fiap.tech.challenge.adapter.driven.payment.gateway.request.PaymentCheckoutResponse;
import br.com.fiap.tech.challenge.adapter.driven.payment.gateway.respose.PaymentConfirmResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MarketPaymentClient", url = "https://api.mercadopago.com")
public interface MarketPaymentClient {


    @PostMapping("/checkout/preferences")
    ResponseEntity<PaymentCheckoutResponse> checkout(
            @RequestHeader("Authorization") String token,
            @RequestBody PaymentCheckoutRequest paymentCheckoutRequest
    );

    @GetMapping("/v1/payments/{paymentId}")
    ResponseEntity<PaymentConfirmResponse> getPayment(
            @RequestHeader("Authorization") String token,
            @PathVariable("paymentId") String paymentId
    );
}