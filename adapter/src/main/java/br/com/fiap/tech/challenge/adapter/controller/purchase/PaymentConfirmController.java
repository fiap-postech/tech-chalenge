package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.application.dto.PaymentConfirmDTO;

public interface PaymentConfirmController {
    PurchaseDTO confirm(PaymentConfirmDTO dto);
}
