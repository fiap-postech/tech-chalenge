package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;

public interface FindAllAvailableProductByCategory {
    ResponseList<Product> list(ProductCategory category, Page page);
}
