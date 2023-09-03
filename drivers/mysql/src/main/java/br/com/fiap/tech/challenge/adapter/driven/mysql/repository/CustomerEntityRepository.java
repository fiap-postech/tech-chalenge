package br.com.fiap.tech.challenge.adapter.driven.mysql.repository;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByUuid(String uuid);
    Optional<CustomerEntity> findByDocumentAndEnabled(String document, boolean enabled);
}
