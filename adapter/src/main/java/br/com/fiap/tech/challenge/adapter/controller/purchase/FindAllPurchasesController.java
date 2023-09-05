package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.application.util.Page;
import br.com.fiap.tech.challenge.application.util.ResponseList;

public interface FindAllPurchasesController {

    ResponseList<PurchaseDTO> list(Page page);
}
