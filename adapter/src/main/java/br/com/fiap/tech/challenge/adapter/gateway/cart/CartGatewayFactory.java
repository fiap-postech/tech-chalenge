package br.com.fiap.tech.challenge.adapter.gateway.cart;

import br.com.fiap.tech.challenge.adapter.repository.CartReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CartWriterRepository;
import br.com.fiap.tech.challenge.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.gateway.CartWriterGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartGatewayFactory {

    public static CartReaderGateway cartReaderGateway(CartReaderRepository repository) {
        return new CartReaderGatewayImpl(repository);
    }

    public static CartWriterGateway cartWriterGateway(CartWriterRepository repository) {
        return new CartWriterGatewayImpl(repository);
    }
}
