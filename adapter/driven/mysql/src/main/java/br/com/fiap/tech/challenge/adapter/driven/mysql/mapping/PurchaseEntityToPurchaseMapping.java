package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseItemEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.PaymentEntityRepository;
import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.domain.Purchase;
import br.com.fiap.tech.challenge.domain.PurchaseItem;
import br.com.fiap.tech.challenge.domain.entity.Payment;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.tech.challenge.error.ApplicationError.PAYMENT_NOT_FOUND;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

@Mapper
@RequiredArgsConstructor
public class PurchaseEntityToPurchaseMapping implements MySQLTypeMapConfiguration {

    private final PaymentEntityRepository paymentRepository;

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(PurchaseEntity.class, Purchase.class)
                .setProvider(purchaseProvider(mapper));
    }

    private Provider<Purchase> purchaseProvider(ModelMapper mapper) {
        return provision -> {
            var request = (PurchaseEntity) provision.getSource();

            return Purchase.builder()
                    .payment(getPayment(request, mapper))
                    .customer(getCustomer(request.getCustomer()))
                    .date(request.getDate())
                    .status(request.getStatus())
                    .items(getItems(request.getItems(), mapper))
                    .uuid(UUID.fromString(request.getUuid()))
                    .build();
        };
    }

    private Payment getPayment(PurchaseEntity purchase, ModelMapper mapper) {
        var entity = paymentRepository.findByPurchaseUuid(purchase.getUuid())
                .orElseThrow(() -> new ApplicationException(PAYMENT_NOT_FOUND));

        return mapper.map(entity, Payment.class);
    }

    private Customer getCustomer(CustomerEntity entity) {
        if (isNull(entity)) {
            return null;
        }

        return entity.toDomain();
    }

    private List<PurchaseItem> getItems(List<PurchaseItemEntity> items, ModelMapper mapper) {
        if (isNull(items) || items.isEmpty()) {
            return emptyList();
        }

        return items.stream()
                .map(item -> mapper.map(item, PurchaseItem.class))
                .toList();
    }
}
