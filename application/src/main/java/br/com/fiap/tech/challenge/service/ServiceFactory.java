package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driven.CustomerWriterService;
import br.com.fiap.tech.challenge.port.driven.ProductReaderService;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.*;

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

    public static CreateCustomerService createCustomerService(CustomerWriterService writer, CustomerReaderService reader){
        return new CreateCustomerServiceImpl(writer, reader);
    }

    public static FindCustomerByDocumentService findCustomerByDocumentService(CustomerReaderService reader){
        return new FindCustomerByDocumentServiceImpl(reader);
    }

}
