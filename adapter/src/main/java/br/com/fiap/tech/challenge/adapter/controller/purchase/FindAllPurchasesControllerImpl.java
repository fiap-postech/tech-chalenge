package br.com.fiap.tech.challenge.adapter.controller.purchase;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindAllPurchasesControllerImpl implements FindAllPurchasesController {

    private final FindAllPurchasesUseCase useCase;
    private final PurchasePresenter presenter;

    @Override
    public ResponseList<PurchaseDTO> list(Page page) {
        return presenter.present(useCase.list(page));
    }
}
