package br.com.fiap.tech.challenge.usecase.product.impl;

import br.com.fiap.tech.challenge.gateway.product.ProductReaderGateway;
import br.com.fiap.tech.challenge.gateway.product.ProductWriterGateway;
import br.com.fiap.tech.challenge.usecase.product.CreateProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.DisableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.EnableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductByCategoryUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindProductByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.product.UpdateProductUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductUseCaseFactory {

    public static FindAllAvailableProductUseCase findAllAvailableProductService(ProductReaderGateway reader) {
        return new FindAllAvailableProductUseCaseImpl(reader);
    }

    public static FindAllAvailableProductByCategoryUseCase findAllAvailableProductByCategory(ProductReaderGateway reader) {
        return new FindAllAvailableProductByCategoryUseCaseImpl(reader);
    }

    public static FindProductByUUIDUseCase findProductByUUIDService(ProductReaderGateway reader) {
        return new FindProductByUUIDUseCaseImpl(reader);
    }

    public static CreateProductUseCase createProductService(ProductWriterGateway writerGateway, ProductReaderGateway readerGateway) {
        return new CreateProductUseCaseImpl(writerGateway, readerGateway);
    }

    public static UpdateProductUseCase updateProductService(ProductReaderGateway reader, ProductWriterGateway writer) {
        return new UpdateProductUseCaseImpl(reader, writer);
    }

    public static EnableProductUseCase enableProductService(ProductWriterGateway writer, ProductReaderGateway reader) {
        return new EnableProductUseCaseImpl(reader, writer);
    }

    public static DisableProductUseCase disableProductService(ProductWriterGateway writer, ProductReaderGateway reader) {
        return new DisableProductUseCaseImpl(reader, writer);
    }
}