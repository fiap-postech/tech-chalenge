package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.PaymentEntityRepository;
import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.domain.entity.Payment;
import br.com.fiap.tech.challenge.domain.entity.Purchase;
import br.com.fiap.tech.challenge.domain.valueobject.PurchaseItem;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static br.com.fiap.tech.challenge.error.ApplicationError.PAYMENT_NOT_FOUND;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class PurchaseMapper {

    @Autowired
    protected PaymentEntityRepository paymentRepository;

    @Autowired
    protected PaymentMapper paymentMapper;

    @Autowired
    protected PurchaseItemMapper purchaseItemMapper;

    public abstract PurchaseEntity toPurchaseEntity (Purchase source);

    @Mapping(target = "payment", source = "source", qualifiedByName = "getPayment")
    @Mapping(target = "customer", source = "source", qualifiedByName = "getCustomer")
    @Mapping(target = "items", source = "source", qualifiedByName = "getItems")
    public abstract Purchase toPurchase(PurchaseEntity source);


    @Named("getPayment")
    Payment getPayment(PurchaseEntity purchase) {
        var entity = paymentRepository.findByPurchaseUuid(purchase.getUuid())
                .orElseThrow(() -> new ApplicationException(PAYMENT_NOT_FOUND));

        return paymentMapper.toPayment(entity);
    }

    @Named("getCustomer")
    Customer getCustomer(PurchaseEntity source) {
        if (isNull(source.getCustomer())) return null;
        return source.getCustomer().toDomain();
    }

    @Named("getItems")
    List<PurchaseItem> getItems(PurchaseEntity source) {
        var items = source.getItems();
        if (isNull(items) || items.isEmpty()) {
            return emptyList();
        }

        return items.stream()
                .map(item -> purchaseItemMapper.toPurchaseItem(item))
                .toList();
    }
}
