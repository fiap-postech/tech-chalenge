package br.com.fiap.tech.challenge.application.usecase.purchase;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.application.util.Page;
import br.com.fiap.tech.challenge.application.util.ResponseList;

public interface FindAllPurchasesUseCase {

    ResponseList<Purchase> list(Page page);
}
