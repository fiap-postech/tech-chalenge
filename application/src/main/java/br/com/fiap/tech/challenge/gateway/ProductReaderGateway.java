package br.com.fiap.tech.challenge.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;

import java.util.UUID;

public interface ProductReaderGateway {

    ResponseList<Product> readAll(Page page);

    ResponseList<Product> readAllByCategory(ProductCategory category, Page page);

    Product readById(UUID id);

}
