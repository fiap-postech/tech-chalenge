package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.application.dto.CreateComboProductDTO;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;

import java.math.BigDecimal;

public class CreateComboProductDTOTestBuilder {

    public static class Builder {
        private CreateComboProductDTO builder;

        public CreateComboProductDTOTestBuilder.Builder withName() {
            builder.setName("Combo Nº1");
            return this;
        }

        public CreateComboProductDTOTestBuilder.Builder withDescription() {
            builder.setDescription("Combo de hamburguer, batata frita e refrigerante");
            return this;
        }

        public CreateComboProductDTOTestBuilder.Builder withPrice() {
            builder.setPrice(BigDecimal.valueOf(30.0));
            return this;
        }

        public CreateComboProductDTOTestBuilder.Builder withImage() {
            builder.setImage("http://localhost:8080/image.jpg");
            return this;
        }

        public CreateComboProductDTOTestBuilder.Builder withCategory() {
            builder.setCategory(ProductCategory.COMBO);
            return this;
        }

        public CreateComboProductDTOTestBuilder.Builder withSandwichId() {
            builder.setSandwichId(SandwichTestBuilder.SANDWICH_UUID.toString());
            return this;
        }

        public CreateComboProductDTOTestBuilder.Builder withBeverageId() {
            builder.setBeverageId(BeverageTestBuilder.BEVERAGE_UUID.toString());
            return this;
        }

        public CreateComboProductDTOTestBuilder.Builder withSideDishId() {
            builder.setSideDishId(SideDishTestBuilder.SIDEDISH_UUID.toString());
            return this;
        }

        private CreateComboProductDTO build(){
            return new CreateComboProductDTO();
        }

        public CreateComboProductDTO fullFields() {
            builder = build();
            withName();
            withDescription();
            withPrice();
            withImage();
            withCategory();
            withSandwichId();
            withBeverageId();
            withSideDishId();
            return builder;
        }
    }
}
