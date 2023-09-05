package br.com.fiap.tech.challenge.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.adapter.repository.PurchaseReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.gateway.PurchaseWriterGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseGatewayFactory {

    public static PurchaseReaderGateway purchaseReaderGateway(PurchaseReaderRepository repository) {
        return new PurchaseReaderGatewayImpl(repository);
    }

    public static PurchaseWriterGateway purchaseWriterGateway(PurchaseWriterRepository repository) {
        return new PurchaseWriterGatewayImpl(repository);
    }

}
