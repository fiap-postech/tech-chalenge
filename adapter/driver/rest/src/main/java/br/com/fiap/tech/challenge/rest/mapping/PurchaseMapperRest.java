package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Purchase;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import br.com.fiap.tech.challenge.rest.resource.response.PaymentResponse;
import br.com.fiap.tech.challenge.rest.resource.response.PurchaseItemResponse;
import br.com.fiap.tech.challenge.rest.resource.response.PurchseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class PurchaseMapperRest {

    @Autowired
    protected PaymentMapperRest paymentMapperRest;

    @Autowired
    protected CustomerMapperRest customerMapperRest;

    @Autowired
    protected PurchaseItemMapperRest purchaseItemMapperRest;


    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "payment", source = "source", qualifiedByName = "getPaymentResponse")
    @Mapping(target = "customer", source = "source", qualifiedByName = "getCustomerResponse")
    @Mapping(target = "items", source = "source", qualifiedByName = "getPurchaseItemResponse")
    public abstract PurchseResponse toPurchseResponse(Purchase source);


    @Named("getPaymentResponse")
    PaymentResponse getPaymentResponse(Purchase source) {
        return paymentMapperRest.toPaymentResponse(source.payment());
    }

    @Named("getCustomerResponse")
    CustomerResponse getCustomerResponse(Purchase source) {
        return customerMapperRest.toCustomerResponse(source.customer());
    }

    @Named("getPurchaseItemResponse")
    List<PurchaseItemResponse> getPurchaseItemResponse(Purchase source) {
        return source.items().stream()
                .map(purchaseItemMapperRest::toPurchaseItemResponse)
                .toList();
    }
}
