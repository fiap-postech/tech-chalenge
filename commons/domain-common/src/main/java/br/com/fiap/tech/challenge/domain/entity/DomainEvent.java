package br.com.fiap.tech.challenge.domain.entity;

import java.time.LocalDateTime;

public interface DomainEvent {
    int version();
    LocalDateTime occurredOn();
}
