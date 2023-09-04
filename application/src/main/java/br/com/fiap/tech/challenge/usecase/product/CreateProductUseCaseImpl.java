package br.com.fiap.tech.challenge.usecase.product;

import br.com.fiap.tech.challenge.dto.CreateComboProductDTO;
import br.com.fiap.tech.challenge.dto.CreateProductDTO;
import br.com.fiap.tech.challenge.dto.CreateSingleProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Beverage;
import br.com.fiap.tech.challenge.enterprise.entity.Combo;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.entity.Sandwich;
import br.com.fiap.tech.challenge.enterprise.entity.SideDish;
import br.com.fiap.tech.challenge.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.gateway.ProductWriterGateway;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class CreateProductUseCaseImpl implements CreateProductUseCase {

    private ProductWriterGateway writerGateway;
    private ProductReaderGateway readerGateway;

    @Override
    public Product create(CreateProductDTO dto) {
        if (dto instanceof CreateComboProductDTO requestCombo) {
            return createComboProduct(requestCombo);
        }

        if (dto instanceof CreateSingleProductDTO requestSingle) {
            return createSingleProduct(requestSingle);
        }

        throw new IllegalStateException();
    }

    private Product createSingleProduct(CreateSingleProductDTO dto) {
        return writerGateway.write(dto.toDomain());
    }

    private Product createComboProduct(CreateComboProductDTO dto) {
        var product = (Combo) dto.toDomain();

        product = product.toBuilder()
                .beverage((Beverage) readerGateway.readById(UUID.fromString(dto.getBeverageId())))
                .sandwich((Sandwich) readerGateway.readById(UUID.fromString(dto.getSandwichId())))
                .sideDish((SideDish) readerGateway.readById(UUID.fromString(dto.getSideDishId())))
                .build();

        product.validate();

        return writerGateway.write(product);
    }
}
