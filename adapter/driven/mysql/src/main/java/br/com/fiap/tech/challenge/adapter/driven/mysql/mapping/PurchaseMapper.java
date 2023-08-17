package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.domain.entity.Purchase;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PurchaseMapper {


    PurchaseEntity toPurchase (Purchase source);

    Purchase toPurchaseEntity(PurchaseEntity source);
}
