package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.mapping.PurchaseMapper;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.PurchaseEntityRepository;
import br.com.fiap.tech.challenge.domain.entity.Purchase;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.PurchaseReaderService;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Function;

import static br.com.fiap.tech.challenge.error.ApplicationError.PURCHASE_NOT_FOUND_BY_UUID;

@Service
public class PurchaseEntityReaderService implements PurchaseReaderService {

    private final PurchaseMapper purchaseMapper;
    private final PurchaseEntityRepository repository;

    public PurchaseEntityReaderService(PurchaseMapper purchaseMapper, PurchaseEntityRepository repository) {
        this.purchaseMapper = purchaseMapper;
        this.repository = repository;
    }

    @Override
    public ResponseList<Purchase> readAll(Page page) {
        return readAll(page, repository::findAll);
    }

    @Override
    public Purchase readById(UUID id) {
        return repository.findByUuid(id.toString())
                .map(entity -> entity.toDomain(purchaseMapper))
                .orElseThrow(() -> new ApplicationException(PURCHASE_NOT_FOUND_BY_UUID, id.toString()));
    }

    private ResponseList<Purchase> readAll(Page page, Function<Pageable, org.springframework.data.domain.Page<PurchaseEntity>> reader) {
        var result = reader.apply(PageRequest.of(page.number(), page.size()));

        return new ResponseList<>(
                result.getNumber(),
                result.getSize(),
                result.getNumberOfElements(),
                result.getTotalElements(),
                result.getContent().stream().map(p -> p.toDomain(purchaseMapper)).toList()
        );
    }
}
