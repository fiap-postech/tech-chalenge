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

    @Schema(description = "Identificador do produto", example = "12d1b555-6b86-41d8-afb6-17a01b293869")
    private String id;

    @Schema(description = "Nome do produto", example = "hamburguer de carne")
    private String name;

    @Schema(description = "Categoria do produto", example = "SANDWICH")
    private ProductCategory category;

    @Schema(description = "Descrição do produto", example = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim")
    private String description;

    @Schema(description = "Link da imagem do produto", example = "http://cdn:8888/x-burger.png")
    private String image;

    @Schema(description = "Valor do produto", example = "9.05")
    private BigDecimal price;

    @Schema(description = "Valor total do produto", example = "10.95")
    private BigDecimal fullPrice;

    @Schema(description = "Valor do desconto do produto", example = "1.90")
    private BigDecimal discount;
}