package br.com.fiap.tech.challenge.usecase.purchase;

import br.com.fiap.tech.challenge.dto.PaymentConfirmDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

public interface PaymentConfirmUseCase {

    Purchase confirm(PaymentConfirmDTO dto);

}
