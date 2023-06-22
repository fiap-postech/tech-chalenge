package br.com.fiap.tech.challenge.rest.resource.response;

import br.com.fiap.tech.challenge.domain.ProductCategory;
import br.com.fiap.tech.challenge.rest.common.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um Produto")
public class ProductResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    @Schema(description = "Identificador do produto", example = "1")
    private String id;
    @Schema(description = "Nome do produto", example = "hamburguer de carne")
    private String name;
    @Schema(description = "Descrição do produto", example = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim")
    private String description;
    @Schema(description = "Link da imagem do produto", example = "http://localhost:8080/foto")
    private String image;
    @Schema(description = "Preço do produto", example = "23.48")
    private BigDecimal price;
    @Schema(description = "Categoria do produto", example = "SANDWICH")
    private ProductCategory category;
}
