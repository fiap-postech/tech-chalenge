package br.com.fiap.tech.challenge.usecase.product;

import br.com.fiap.tech.challenge.dto.CreateProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Product;

public interface CreateProductUseCase {

    Product create(CreateProductDTO dto);

}
