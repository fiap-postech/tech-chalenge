package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.application.dto.CreateSingleProductDTO;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;

import java.math.BigDecimal;

public class CreateSandwichProdutDTOTestBuilder {

    public static class Builder {
        private CreateSingleProductDTO builder;

        public CreateSandwichProdutDTOTestBuilder.Builder withName() {
            builder.setName("Hamburguer Delicioso");
            return this;
        }

        public CreateSandwichProdutDTOTestBuilder.Builder withDescription() {
            builder.setDescription("Saboroso hamburguer do chefe");
            return this;
        }

        public CreateSandwichProdutDTOTestBuilder.Builder withPrice() {
            builder.setPrice(BigDecimal.valueOf(18.0));
            return this;
        }

        public CreateSandwichProdutDTOTestBuilder.Builder withImage() {
            builder.setImage("http://localhost:8080/image.jpg");
            return this;
        }

        public CreateSandwichProdutDTOTestBuilder.Builder withCategory() {
            builder.setCategory(ProductCategory.SANDWICH);
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
