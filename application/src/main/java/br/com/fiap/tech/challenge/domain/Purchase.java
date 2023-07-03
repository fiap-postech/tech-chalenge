package br.com.fiap.tech.challenge.domain;

import br.com.fiap.tech.challenge.domain.entity.Payment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class Purchase extends Entity {

    @Serial
    private static final long serialVersionUID = -9196907733871633595L;

    private final Customer customer;

    @NotNull
    private final PurchaseStatus status;

    @NotNull
    private final LocalDate date;

    @NotNull
    @NotEmpty
    @Valid
    private final List<PurchaseItem> items;

    @NotNull
    private final Payment payment;

    @Builder(toBuilder = true)
    public Purchase(@Builder.ObtainVia(method = "uuid") UUID uuid,
                    Customer customer,
                    @NotNull PurchaseStatus status,
                    @NotNull LocalDate date,
                    @NotNull List<PurchaseItem> items,
                    @NotNull Payment payment) {
        super(uuid);

        this.customer = customer;
        this.status = status;
        this.date = date;
        this.items = items;
        this.payment = payment;

        validate();
    }

    public Purchase made() {
        return toBuilder()
                .status(PurchaseStatus.MADE)
                .build();
    }

    public Purchase delivered() {
        return toBuilder()
                .status(PurchaseStatus.DELIVERED)
                .build();
    }

    public Purchase finished() {
        return toBuilder()
                .status(PurchaseStatus.FINISHED)
                .build();
    }

    public Purchase addItem(Product product, Quantity quantity) {
        var item = PurchaseItem.builder()
                .product(product)
                .quantity(quantity)
                .price(product.price())
                .fullPrice(product.fullPrice())
                .discount(product.discount())
                .build();

        var itemList = items();
        itemList.add(item);

        return toBuilder().items(itemList).build();
    }

    public static Purchase newPurchase(Cart cart, Payment payment) {
        return Purchase.builder()
                .date(LocalDate.now())
                .customer(cart.customer())
                .status(PurchaseStatus.PAID)
                .items(
                        cart.items()
                                .stream()
                                .map(PurchaseItem::of)
                                .toList()
                )
                .payment(payment)
                .build();
    }
}
