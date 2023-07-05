package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Purchase;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.PurchseResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Mapper
@RequiredArgsConstructor
public class PurchaseToPurchaseResponseMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Purchase.class, PurchseResponse.class)
                .addMapping(Purchase::uuid, PurchseResponse::setId)
                .addMapping(Purchase::status, PurchseResponse::setStatus)
                .addMapping(Purchase::date, PurchseResponse::setDate)
                .addMapping(Purchase::payment, PurchseResponse::setPayment)
                .addMapping(Purchase::customer, PurchseResponse::setCustomer)
                .addMapping(Purchase::items, PurchseResponse::setItems);
    }
}
