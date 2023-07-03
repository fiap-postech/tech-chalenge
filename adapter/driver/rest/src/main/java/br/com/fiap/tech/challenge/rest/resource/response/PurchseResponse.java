package br.com.fiap.tech.challenge.rest.resource.response;

import br.com.fiap.tech.challenge.domain.PurchaseStatus;
import br.com.fiap.tech.challenge.rest.common.response.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PurchseResponse extends Response {
    @Serial
    private static final long serialVersionUID = -477119349237609062L;

    private String id;
    private CustomerResponse customer;
    private PurchaseStatus status;
    private LocalDate date;
    private List<PurchaseItemResponse> items;
    private PaymentResponse payment;
}
