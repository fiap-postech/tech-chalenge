package br.com.fiap.tech.challenge.adapter.driven.mysql.model;

import br.com.fiap.tech.challenge.domain.entity.Purchase;
import br.com.fiap.tech.challenge.domain.enums.PurchaseStatus;
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
import org.modelmapper.ModelMapper;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

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
    @OneToMany(mappedBy = "purchase", cascade = ALL)
    private List<PurchaseItemEntity> items = new ArrayList<>();

    public void addItem(PurchaseItemEntity item) {
        item.setPurchase(this);
        getItems().add(item);
    }

    public void setItems(List<PurchaseItemEntity> items) {
        this.items = items;
        this.items.forEach(i -> i.setPurchase(this));
    }

    public Purchase toDomain(ModelMapper mapper) {
        return mapper.map(this, Purchase.class);
    }
}
