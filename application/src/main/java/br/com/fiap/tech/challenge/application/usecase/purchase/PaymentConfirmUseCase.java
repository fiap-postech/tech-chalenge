package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.application.dto.PaymentConfirmDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

public interface PaymentConfirmUseCase {

    Purchase confirm(PaymentConfirmDTO dto);

}
