package br.com.fiap.tech.challenge.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.adapter.mapping.PurchaseMapper;
import br.com.fiap.tech.challenge.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.gateway.PurchaseWriterGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PurchaseWriterGatewayImpl implements PurchaseWriterGateway {

    private final PurchaseWriterRepository repository;

    @Override
    public Purchase write(Purchase purchase) {
        var mapper = PurchaseMapper.INSTANCE;

        return mapper.toDomain(repository.write(mapper.toDTO(purchase)));
    }
}
