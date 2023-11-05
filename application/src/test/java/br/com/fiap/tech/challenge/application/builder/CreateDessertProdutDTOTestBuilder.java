package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.application.dto.CreateSingleProductDTO;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;

import java.math.BigDecimal;

public class CreateDessertProdutDTOTestBuilder {

    public static class Builder {
        private CreateSingleProductDTO builder;

        public CreateDessertProdutDTOTestBuilder.Builder withName() {
            builder.setName("Tortinha de Maça");
            return this;
        }

        public CreateDessertProdutDTOTestBuilder.Builder withDescription() {
            builder.setDescription("Saborosa tortinha de maça");
            return this;
        }

        public CreateDessertProdutDTOTestBuilder.Builder withPrice() {
            builder.setPrice(BigDecimal.valueOf(8.0));
            return this;
        }

        public CreateDessertProdutDTOTestBuilder.Builder withImage() {
            builder.setImage("http://localhost:8080/image.jpg");
            return this;
        }

        public CreateDessertProdutDTOTestBuilder.Builder withCategory() {
            builder.setCategory(ProductCategory.DESSERT);
            return this;
        }

        private CreateSingleProductDTO build(){
            return new CreateSingleProductDTO();
        }

        public CreateSingleProductDTO fullFields(){
            builder = build();
            withName();
            withDescription();
            withCategory();
            withImage();
            withPrice();
            return builder;
        }
    }
}
