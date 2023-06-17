package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.port.driver.ProductReaderService;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static ProductReaderService productReaderService(){
        return new ProductReaderServiceImpl();
    }
}
