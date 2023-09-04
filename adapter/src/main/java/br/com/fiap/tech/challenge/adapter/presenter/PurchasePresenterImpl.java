package br.com.fiap.tech.challenge.adapter.presenter;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.adapter.mapping.PurchaseMapper;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.util.ResponseList;

class PurchasePresenterImpl implements PurchasePresenter{
    @Override
    public PurchaseDTO present(Purchase purchase) {
        return PurchaseMapper.INSTANCE.toDTO(purchase);
    }

    @Override
    public ResponseList<PurchaseDTO> present(ResponseList<Purchase> list) {
        return ResponseList.from(list, PurchaseMapper.INSTANCE::toDTO);
    }
}
