package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.domain.Purchase;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;

@Mapper
public class PurchaseToPurchaseEntityMapping implements MySQLTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Purchase.class, PurchaseEntity.class)
                .addMapping(Purchase::date, PurchaseEntity::setDate)
                .addMapping(Purchase::status, PurchaseEntity::setStatus)
                .addMapping(Purchase::items, PurchaseEntity::setItems);
    }
}
