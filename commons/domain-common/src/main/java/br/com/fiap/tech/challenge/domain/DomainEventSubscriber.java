package br.com.fiap.tech.challenge.domain;

public interface DomainEventSubscriber<T> {
    void handle(final T event);
    Class<T> subscribedToEventType();
}
