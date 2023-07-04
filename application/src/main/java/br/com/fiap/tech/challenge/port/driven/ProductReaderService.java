package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.domain.enums.ProductCategory;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;

import java.util.UUID;

public interface ProductReaderService {

    ResponseList<Product> readAll(Page page);

    ResponseList<Product> readAllByCategory(ProductCategory category, Page page);

    Product readById(UUID id);

}
