package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.application.dto.CreateSingleProductDTO;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;

import java.math.BigDecimal;

public class CreateSideDishProdutDTOTestBuilder {

    public static class Builder {
        private CreateSingleProductDTO builder;

        public CreateSideDishProdutDTOTestBuilder.Builder withName() {
            builder.setName("Batata Frita Crocrante");
            return this;
        }

        public CreateSideDishProdutDTOTestBuilder.Builder withDescription() {
            builder.setDescription("Batata Frita Crocrante e gostosa");
            return this;
        }

        public CreateSideDishProdutDTOTestBuilder.Builder withPrice() {
            builder.setPrice(BigDecimal.valueOf(7.0));
            return this;
        }

        public CreateSideDishProdutDTOTestBuilder.Builder withImage() {
            builder.setImage("http://localhost:8080/image.jpg");
            return this;
        }

        public CreateSideDishProdutDTOTestBuilder.Builder withCategory() {
            builder.setCategory(ProductCategory.SIDE_DISH);
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
