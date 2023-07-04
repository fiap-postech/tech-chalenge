package br.com.fiap.tech.challenge.adapter.driven.redis.service;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.repository.CartEntityRepository;
import br.com.fiap.tech.challenge.domain.entity.Cart;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisModelMapperConfiguration.REDIS_MODEL_MAPPER;
import static br.com.fiap.tech.challenge.error.ApplicationError.CART_NOT_FOUND_BY_UUID;

@Service
public class CartEntityService implements CartReaderService, CartWriterService {

    private final CartEntityRepository cartEntityRepository;
    private final ModelMapper mapper;

    public CartEntityService(CartEntityRepository cartEntityRepository, @Qualifier(REDIS_MODEL_MAPPER) ModelMapper mapper) {
        this.cartEntityRepository = cartEntityRepository;
        this.mapper = mapper;
    }

    @Override
    public Cart readById(UUID id) {
        var cartEntity = cartEntityRepository.findById(id.toString())
                .orElseThrow(() -> new ApplicationException(CART_NOT_FOUND_BY_UUID, id.toString()));

        return cartEntity.toDomain(mapper);
    }

    @Override
    public Cart write(Cart cart) {
        var cartEntity = cartEntityRepository.save(mapper.map(cart, CartEntity.class));
        return cartEntity.toDomain(mapper);
    }
}