package br.com.fiap.tech.challenge.adapter.driven.redis.service;

import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.CartItemMapper;
import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.CartMapper;
import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.CustomerMapper;
import br.com.fiap.tech.challenge.adapter.driven.redis.repository.CartEntityRepository;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.gateway.CartWriterGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CART_NOT_FOUND_BY_UUID;

@Service
public class CartEntityGateway implements CartReaderGateway, CartWriterGateway {

    private final CartEntityRepository cartEntityRepository;
    private final CartMapper cartMapper;
    private final CustomerMapper customerMapper;
    private final CartItemMapper cartItemMapper;

    public CartEntityGateway(
            CartEntityRepository cartEntityRepository,
            CartMapper cartMapper,
            CustomerMapper customerMapper,
            CartItemMapper cartItemMapper
    ) {
        this.cartEntityRepository = cartEntityRepository;
        this.cartMapper = cartMapper;
        this.customerMapper = customerMapper;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public Cart readById(UUID id) {
        var cartEntity = cartEntityRepository.findById(id.toString())
                .orElseThrow(() -> new ApplicationException(CART_NOT_FOUND_BY_UUID, id.toString()));

        return cartEntity.toDomain(cartItemMapper, customerMapper);
    }

    @Override
    public Cart write(Cart cart) {
        var cartEntity = cartEntityRepository.save(cartMapper.toCartEntity(cart));
        return cartEntity.toDomain(cartItemMapper, customerMapper);
    }
}