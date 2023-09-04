package br.com.fiap.tech.challenge.adapter.dto;

import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentStatus;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentDTO implements Serializable {
    private String id;
    private LocalDate date;
    private PaymentStatus status;
    private PaymentMethod method;
    private String urlPayment;
    private BigDecimal amount;
}
