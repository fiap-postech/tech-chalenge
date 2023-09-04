package br.com.fiap.tech.challenge.usecase.cart;

import br.com.fiap.tech.challenge.dto.CreateCartDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.usecase.customer.FindCustomerByUUIDUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CART_CUSTOMER_NOT_AVAILABLE;
import static br.com.fiap.tech.challenge.util.Validations.validate;

@RequiredArgsConstructor
class CreateCartUseCaseImpl implements CreateCartUseCase {

    private final FindCustomerByUUIDUseCase findCustomerByUUIDUseCase;
    private final CartWriterGateway writerService;

    @Override
    public Cart create(CreateCartDTO dto) {
        validate(dto);

        var customer = findCustomerByUUIDUseCase.get(UUID.fromString(dto.getCustomerId()));

        if (!customer.enabled()) {
            throw new ApplicationException(CART_CUSTOMER_NOT_AVAILABLE, dto.getCustomerId());
        }

        var cart = Cart.builder()
                .customer(customer)
                .build();

        return writerService.write(cart);
    }
}