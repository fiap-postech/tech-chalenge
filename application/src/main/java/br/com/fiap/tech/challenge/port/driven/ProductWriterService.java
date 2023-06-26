package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Product;

public interface ProductWriterService {

    Product write(Product product);

    Product enable(Product product);

    Product disable(Product product);

}
