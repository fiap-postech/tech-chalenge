package br.com.fiap.tech.challenge.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class Payment extends Entity {

    @Serial
    private static final long serialVersionUID = 2733420553391362792L;

    @NotNull
    private final LocalDate date;

    @NotNull
    private final PaymentStatus status;

    @NotNull
    private final PaymentMethod method;

    private String urlPayment;

    @NotNull
    @Positive
    private final Money amount;

    @Builder(toBuilder = true)
    public Payment(@Builder.ObtainVia(method = "uuid") UUID uuid,
                   @NotNull LocalDate date,
                   @NotNull PaymentStatus status,
                   @NotNull PaymentMethod method,
                   @NotNull Money amount) {
        super(uuid);

        this.date = date;
        this.status = status;
        this.method = method;
        this.amount = amount;

        validate();
    }

    public boolean isSuccessful() {
        return PaymentStatus.PAID.equals(status());
    }

    public boolean isError() {
        return PaymentStatus.ERROR.equals(status());
    }

    public Payment paid() {
        return toBuilder()
                .status(PaymentStatus.PAID)
                .build();
    }

    public Payment error() {
        return toBuilder()
                .status(PaymentStatus.ERROR)
                .build();
    }

    public void setUrlPayment(String urlPayment) {
        this.urlPayment = urlPayment;
    }
}
