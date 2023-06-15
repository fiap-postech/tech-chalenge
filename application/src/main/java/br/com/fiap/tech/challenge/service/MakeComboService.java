package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.DomainEventSubscriber;

public class MakeComboService implements DomainEventSubscriber<String> {
    @Override
    public void handle(String event) {

    }

    @Override
    public Class<String> subscribedToEventType() {
        return null;
    }
}
