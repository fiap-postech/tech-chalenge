package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseItemEntity;
import br.com.fiap.tech.challenge.domain.valueobject.PurchaseItem;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PurchaseItemMapper {

    PurchaseItemEntity toPurchaseItemEntity(PurchaseItem source);

    PurchaseItem toPurchaseItem(PurchaseItemEntity source);


}
