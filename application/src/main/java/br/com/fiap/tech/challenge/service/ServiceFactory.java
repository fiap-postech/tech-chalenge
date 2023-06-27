package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.AddCartItemService;
import br.com.fiap.tech.challenge.port.driver.CreateCartService;
import br.com.fiap.tech.challenge.port.driver.CreateProductService;
import br.com.fiap.tech.challenge.port.driver.FindAllAvailableProductService;
import br.com.fiap.tech.challenge.port.driver.FindCartByUUIDService;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.port.driver.RemoveCartItemService;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static FindAllAvailableProductService findAllAvailableProductService(ProductReaderService reader) {
        return new FindAllAvailableProductServiceImpl(reader);
    }

    public static FindProductByUUIDService findProductByUUIDService(ProductReaderService reader) {
        return new FindProductByUUIDServiceImpl(reader);
    }

    public static CreateProductService createProductService(ProductWriterService writer) {
        return new CreateProductServiceImpl(writer);
    }

    public static FindCartByUUIDService findCartByUUIDService(CartReaderService reader) {
        return new FindCartByUUIDServiceImpl(reader);
    }

    public static CreateCartService createCartService(CartWriterService writer) {
        return new CreateCartServiceImpl(writer);
    }

    public  static AddCartItemService addCartItemService(CartReaderService reader, CartWriterService writer, FindProductByUUIDService findProductByUUIDService) {
        return new AddCartItemServiceImpl(reader, writer, findProductByUUIDService);
    }

    public  static RemoveCartItemService removeCartItemService(CartWriterService writer) {
        return new RemoveCartItemServiceImpl(writer);
    }
}