package br.com.fiap.tech.challenge.mapper;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.usecase.product.FindProductByUUIDUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static java.util.Objects.isNull;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {

    private static FindProductByUUIDUseCase findProductByUUIDUseCase;

    public static Product getProduct(String productId) {
        return isNull(productId) ? null : findProductByUUIDUseCase.get(UUID.fromString(productId));
    }


    public static void init(FindProductByUUIDUseCase service) {
        findProductByUUIDUseCase = service;
    }
}
