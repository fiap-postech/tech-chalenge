package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.mapping.DBPurchaseMapper;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.PurchaseEntityRepository;
import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.adapter.repository.PurchaseReaderRepository;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static br.com.fiap.tech.challenge.enterprise.enums.PurchaseStatus.FINISHED;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.PURCHASE_NOT_FOUND_BY_UUID;
import static java.util.Comparator.comparing;

@Service
@RequiredArgsConstructor
public class DBPurchaseEntityReaderRepositoryImpl implements PurchaseReaderRepository {

    private final DBPurchaseMapper dbPurchaseMapper;
    private final PurchaseEntityRepository repository;

    @Override
    public ResponseList<PurchaseDTO> readAll(Page page) {
        return readAll(page, repository::findAll);
    }

    @Override
    public PurchaseDTO readById(String id) {
        return repository.findByUuid(id)
                .map(dbPurchaseMapper::toDTO)
                .orElseThrow(() -> new ApplicationException(PURCHASE_NOT_FOUND_BY_UUID, id));
    }

    private ResponseList<PurchaseDTO> readAll(Page page, Function<Pageable, org.springframework.data.domain.Page<PurchaseEntity>> reader) {
        var result = reader.apply(PageRequest.of(page.number(), page.size()));

        return new ResponseList<>(
                result.getNumber(),
                result.getSize(),
                result.getNumberOfElements(),
                result.getTotalElements(),
                getPurchases(result)
        );
    }

    private List<PurchaseDTO> getPurchases(org.springframework.data.domain.Page<PurchaseEntity> result) {
        return result.getContent().stream()
                .map(dbPurchaseMapper::toDTO)
                .filter(p -> p.getStatus() != FINISHED)
                .sorted(comparing(p -> p.getStatus().toString()))
                .toList();
    }
}
