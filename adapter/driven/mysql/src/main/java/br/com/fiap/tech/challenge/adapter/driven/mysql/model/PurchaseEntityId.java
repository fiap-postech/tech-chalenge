package br.com.fiap.tech.challenge.adapter.driven.mysql.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PurchaseEntityId implements Serializable {

    @Serial
    private static final long serialVersionUID = -6903805050356181525L;

    private Long product;
    private Long purchase;
}
