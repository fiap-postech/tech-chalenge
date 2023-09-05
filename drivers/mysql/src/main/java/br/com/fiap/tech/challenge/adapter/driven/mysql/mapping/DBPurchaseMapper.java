package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.CustomerEntityRepository;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.PaymentEntityRepository;
import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.dto.PaymentDTO;
import br.com.fiap.tech.challenge.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.PAYMENT_NOT_FOUND;
import static java.util.Objects.isNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { DBCustomerMapper.class, DBPurchaseItemMapper.class })
public abstract class DBPurchaseMapper {

    @Autowired
    protected PaymentEntityRepository paymentRepository;

    @Autowired
    private CustomerEntityRepository customerRepository;

    @Autowired
    private DBPaymentMapper paymentMapper;

    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "customer", source = "customer", qualifiedByName = "getCustomerEntity")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    public abstract PurchaseEntity toEntity(PurchaseDTO source);

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "payment", source = "source", qualifiedByName = "getPayment")
    public abstract PurchaseDTO toDTO(PurchaseEntity source);


    @Named("getPayment")
    PaymentDTO getPayment(PurchaseEntity purchase) {
        var entity = paymentRepository.findByPurchaseUuid(purchase.getUuid())
                .orElseThrow(() -> new ApplicationException(PAYMENT_NOT_FOUND));

        return paymentMapper.toDTO(entity);
    }

    @Named("getCustomerEntity")
    CustomerEntity getCustomerEntity(CustomerDTO source) {
        if (isNull(source)) return null;

        return customerRepository.findByUuid(source.getId())
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, source.getId()));
    }
}
