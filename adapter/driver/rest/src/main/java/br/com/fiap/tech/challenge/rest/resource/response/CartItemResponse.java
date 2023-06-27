package br.com.fiap.tech.challenge.rest.resource.response;

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
@Schema(description = "Representação de um Item do Carrinho")
public class CartItemResponse extends Response {

    @Serial
    private static final long serialVersionUID = 5960662426733468603L;

    private String id;
    private ProductResponse product;
    private BigDecimal price;
    private BigDecimal discount;
    private int quantity;

}