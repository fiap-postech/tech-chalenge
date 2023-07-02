package br.com.fiap.tech.challenge.adapter.driven.mysql.repository;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseEntityRepository extends JpaRepository<PurchaseEntity, Long> {
}
