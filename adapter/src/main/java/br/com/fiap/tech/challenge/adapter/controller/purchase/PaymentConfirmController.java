package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.dto.PaymentConfirmDTO;

public interface PaymentConfirmController {
    PurchaseDTO confirm(PaymentConfirmDTO dto);
}
