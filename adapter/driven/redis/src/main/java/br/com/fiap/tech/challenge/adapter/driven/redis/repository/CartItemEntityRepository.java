package br.com.fiap.tech.challenge.adapter.driven.redis.repository;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemEntityRepository extends CrudRepository<CartItemEntity, String> {

    List<CartItemEntity> findByCartId(String cartId);
    Optional<CartItemEntity> findByIdAndCartId(String id, String cartId);
}