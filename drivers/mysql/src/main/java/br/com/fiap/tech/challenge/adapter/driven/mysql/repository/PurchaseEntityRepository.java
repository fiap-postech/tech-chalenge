package br.com.fiap.tech.challenge.adapter.driven.mysql.repository;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseEntityRepository extends JpaRepository<PurchaseEntity, Long> {

    Optional<PurchaseEntity> findByUuid(String uuid);

}
