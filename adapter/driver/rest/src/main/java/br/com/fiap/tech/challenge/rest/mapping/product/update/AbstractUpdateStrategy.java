package br.com.fiap.tech.challenge.rest.mapping.product.update;

import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Consumer;

abstract class AbstractUpdateStrategy implements UpdateStrategy {

    protected final UpdateProductRequest request;

    protected AbstractUpdateStrategy(UpdateProductRequest request) {
        this.request = request;
    }

    protected AbstractUpdateStrategy updateName(Consumer<String> nameConsumer){
        Optional.ofNullable(request.getName()).ifPresent(nameConsumer);

        return this;
    }

    protected AbstractUpdateStrategy updateDescription(Consumer<String> descriptionConsumer){
        Optional.ofNullable(request.getDescription()).ifPresent(descriptionConsumer);

        return this;
    }

    protected AbstractUpdateStrategy updateImage(Consumer<String> imageConsumer){
        Optional.ofNullable(request.getImage()).ifPresent(imageConsumer);

        return this;
    }

    protected AbstractUpdateStrategy updatePrice(Consumer<BigDecimal> priceConsumer){
        Optional.ofNullable(request.getPrice()).ifPresent(priceConsumer);

        return this;
    }

    protected AbstractUpdateStrategy updateEnabled(Consumer<Boolean> enabledConsumer){
        Optional.ofNullable(request.getEnabled()).ifPresent(enabledConsumer);

        return this;
    }
}
