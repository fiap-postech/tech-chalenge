package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;

public interface FindAllAvailableProductService {

    ResponseList<Product> list(Page page);

}
