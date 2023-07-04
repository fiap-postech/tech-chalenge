package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.CustomerEntityRepository;
import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.domain.entity.Purchase;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;
import static java.util.Objects.isNull;

@Mapper
@RequiredArgsConstructor
public class PurchaseToPurchaseEntityMapping implements MySQLTypeMapConfiguration {

    private final CustomerEntityRepository customerRepository;

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Purchase.class, PurchaseEntity.class)
                .addMapping(Purchase::uuid, PurchaseEntity::setUuid)
                .addMapping(Purchase::date, PurchaseEntity::setDate)
                .addMapping(Purchase::status, PurchaseEntity::setStatus)
                .addMappings(mapping -> mapping.using(customerConverter()).map(Purchase::customer, PurchaseEntity::setCustomer))
                .addMappings(mapping -> mapping.skip(PurchaseEntity::setItems));
    }

    private Converter<Customer, CustomerEntity> customerConverter() {
        return ctx -> {
            var customer = ctx.getSource();

            if (isNull(customer)){
                return null;
            }

            var uuid = customer.uuid().toString();

            return customerRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, uuid));
        };
    }
}
