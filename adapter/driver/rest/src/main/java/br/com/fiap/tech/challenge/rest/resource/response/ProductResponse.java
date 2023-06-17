package br.com.fiap.tech.challenge.rest.resource.response;

import br.com.fiap.tech.challenge.domain.ProductType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    private String id;
    private String name;
    private BigDecimal price;
    private BigDecimal cost;
    private BigDecimal profit;
    private ProductType type;
}
