package br.com.fiap.tech.challenge.adapter.driven.payment.gateway.service;

import br.com.fiap.tech.challenge.adapter.driven.payment.gateway.client.MarketPaymentClient;
import br.com.fiap.tech.challenge.adapter.driven.payment.gateway.request.CheckoutItem;
import br.com.fiap.tech.challenge.adapter.driven.payment.gateway.request.PaymentCheckoutRequest;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.enterprise.valueobject.PurchaseItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class PaidMarket implements PaymentGateway {

    private static String TOKEN = "Bearer TEST-2872653470214651-090218-855c012dc9b265fe038ddb59fee4caa1-264267342";
    private final BeanFactory factory;

    @Override
    public boolean accept(PaymentMethod method) {
        return PaymentMethod.PAID_MARKET.equals(method);
    }

    @Override
    @SuppressWarnings("squid:S112")
    public Optional<String> pay(Purchase purchase) {
        try {
            var id = purchase.uuid().toString();
            var checkoutItems = purchase.items().stream().map(buildCheckoutItems(id)).toList();
            var request = PaymentCheckoutRequest.builder().items(checkoutItems).build();
            var response = current().checkout(TOKEN, request);

            if (response.getStatusCode().is2xxSuccessful()) {
                return Optional.of(requireNonNull(response.getBody()).getSandbox_init_point());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getPurchaseUUID(String paymentId) {
        try {
            var response = current().getPayment(TOKEN, paymentId);

            if (response.getStatusCode().is2xxSuccessful()) {
                return Optional.of(requireNonNull(response.getBody()).getAdditionalInfo().purchaseId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    private static Function<PurchaseItem, CheckoutItem> buildCheckoutItems(String id) {
        return purchaseItem -> {
            var product = purchaseItem.product();
            return CheckoutItem.builder()
                    .id(id + "_" + product.uuid().toString())
                    .title(product.name())
                    .description(product.description())
                    .picture_url(product.image().url())
                    .category_id(product.category().name())
                    .quantity(purchaseItem.quantity().value())
                    .currency_id("BRL")
                    .unit_price(priceToBigDecimalConverter(purchaseItem.price())).build();
        };
    }

    private MarketPaymentClient current() {
        return factory.getBean(MarketPaymentClient.class);
    }
}
