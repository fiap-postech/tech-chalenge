package br.com.fiap.tech.challenge.adapter.driven.redis.service;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartItemEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.repository.CartEntityRepository;
import br.com.fiap.tech.challenge.adapter.driven.redis.repository.CartItemEntityRepository;
import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.port.driven.CartReaderService;
import br.com.fiap.tech.challenge.port.driven.CartWriterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisModelMapperConfiguration.REDIS_MODEL_MAPPER;
import static br.com.fiap.tech.challenge.error.ApplicationError.CART_ITEM_NOT_FOUND_BY_CART_UUID_AND_CART_ITEM_UUID;
import static br.com.fiap.tech.challenge.error.ApplicationError.CART_NOT_FOUND_BY_UUID;

@Service
public class CartEntityService implements CartReaderService, CartWriterService {

    private final CartEntityRepository cartEntityRepository;
    private final CartItemEntityRepository cartItemEntityRepository;
    private final ModelMapper mapper;

    public CartEntityService(CartEntityRepository cartEntityRepository, CartItemEntityRepository cartItemEntityRepository, @Qualifier(REDIS_MODEL_MAPPER) ModelMapper mapper) {
        this.cartEntityRepository = cartEntityRepository;
        this.cartItemEntityRepository = cartItemEntityRepository;
        this.mapper = mapper;
    }

    @Override
    public Cart readById(UUID id) {
        var cartEntity = cartEntityRepository.findById(id.toString())
                .orElseThrow(() -> new ApplicationException(CART_NOT_FOUND_BY_UUID, id.toString()));

        var items = cartItemEntityRepository.findByCartId(cartEntity.getId());
        cartEntity.setItems(items);

        return cartEntity.toDomain(mapper);
    }

    @Override
    public Cart write(Cart cart) {
        var cartEntity = cartEntityRepository.save(mapper.map(cart, CartEntity.class));
        return cartEntity.toDomain(mapper);
    }

    @Override
    public Cart saveItem(Cart cart, CartItem item) {
        var cartItemEntity = mapper.map(item, CartItemEntity.class);
        cartItemEntity.setCartId(cart.uuid().toString());
        cartItemEntityRepository.save(cartItemEntity);
        return this.readById(cart.uuid());
    }

    @Override
    public Cart deleteItem(UUID cartId, UUID cartItemId) {
        var cartItemEntity = cartItemEntityRepository.findByIdAndCartId(cartItemId.toString(), cartId.toString())
                .orElseThrow(() -> new ApplicationException(CART_ITEM_NOT_FOUND_BY_CART_UUID_AND_CART_ITEM_UUID,
                        cartId.toString(), cartItemId.toString()));

        cartItemEntityRepository.delete(cartItemEntity);

        return this.readById(cartId);
    }
}