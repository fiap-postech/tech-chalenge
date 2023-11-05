package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.application.dto.CreateSingleProductDTO;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;

import java.math.BigDecimal;

public class CreateBeverageProdutDTOTestBuilder {

    public static class Builder {
        private CreateSingleProductDTO builder;

        public CreateBeverageProdutDTOTestBuilder.Builder withName() {
            builder.setName("Refrigerante refrescante");
            return this;
        }

        public CreateBeverageProdutDTOTestBuilder.Builder withDescription() {
            builder.setDescription("O refrigerante mais refrescante que as pessoas gostam");
            return this;
        }

        public CreateBeverageProdutDTOTestBuilder.Builder withPrice() {
            builder.setPrice(BigDecimal.TEN);
            return this;
        }

        public CreateBeverageProdutDTOTestBuilder.Builder withImage() {
            builder.setImage("http://localhost:8080/image.jpg");
            return this;
        }

        public CreateBeverageProdutDTOTestBuilder.Builder withCategory() {
            builder.setCategory(ProductCategory.BEVERAGE);
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
