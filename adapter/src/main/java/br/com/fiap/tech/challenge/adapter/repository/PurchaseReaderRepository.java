package br.com.fiap.tech.challenge.adapter.repository;

import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.application.util.Page;
import br.com.fiap.tech.challenge.application.util.ResponseList;

public interface PurchaseReaderRepository {

    ResponseList<PurchaseDTO> readAll(Page page);

    PurchaseDTO readById(String id);

}
