package br.com.fiap.tech.challenge.adapter.driven.mysql.repository;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, Long> {

    Optional<PaymentEntity> findByPurchaseUuid(String uuid);

}
