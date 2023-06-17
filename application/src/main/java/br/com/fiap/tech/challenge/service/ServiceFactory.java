package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.port.driver.QueryProductService;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static QueryProductService queryProductService(){
        return new QueryProductServiceImpl();
    }
}
