package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PaymentEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseItemEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.PaymentEntityRepository;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.PurchaseEntityRepository;
import br.com.fiap.tech.challenge.domain.Purchase;
import br.com.fiap.tech.challenge.port.driven.PurchaseWriterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLModelMapperConfiguration.MYSQL_MODEL_MAPPER;

@Service
public class PurchaseEntityWriterService implements PurchaseWriterService {

    private final ModelMapper mapper;
    private final PurchaseEntityRepository purchaseRepository;
    private final PaymentEntityRepository paymentRepository;

    public PurchaseEntityWriterService(@Qualifier(MYSQL_MODEL_MAPPER) ModelMapper mapper,
                                       PurchaseEntityRepository purchaseRepository,
                                       PaymentEntityRepository paymentRepository) {
        this.mapper = mapper;
        this.purchaseRepository = purchaseRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public Purchase write(Purchase purchase) {
        var purchaseEntity = savePurchase(purchase);

        var paymentEntity = mapper.map(purchase.payment(), PaymentEntity.class);
        paymentEntity.setPurchase(purchaseEntity);

        paymentRepository.save(paymentEntity);

        return purchaseEntity.toDomain(mapper);
    }

    private PurchaseEntity savePurchase(Purchase purchase) {
        var purchaseEntity = mapper.map(purchase, PurchaseEntity.class);

        purchase.items().stream()
                .map(item -> mapper.map(item, PurchaseItemEntity.class))
                .forEach(purchaseEntity::addItem);

        return purchaseRepository.save(purchaseEntity);
    }
}
