package br.com.fiap.tech.challenge.adapter.driven.mysql.model;

import br.com.fiap.tech.challenge.domain.PurchaseStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@ToString
public class PurchaseEntity extends JPAEntity {
    @Serial
    private static final long serialVersionUID = -4325275215767646995L;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    @NotNull
    private LocalDate date;

    @NotNull
    @OneToMany(mappedBy = "purchase")
    private List<PurchaseItemEntity> items = new ArrayList<>();

    public PurchaseEntity addItem(PurchaseItemEntity item) {
        getItems().add(item);
        return this;
    }

    public void setItems(List<PurchaseItemEntity> items) {
        this.items = items;
        this.items.forEach(i -> i.setPurchase(this));
    }
}
