package br.com.fiap.tech.challenge.adapter.presenter;

import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Product;

public interface ProductPresenter {

    ProductDTO present(Product product);

}
