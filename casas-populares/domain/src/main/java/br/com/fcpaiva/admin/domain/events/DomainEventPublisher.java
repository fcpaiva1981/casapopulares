package br.com.fcpaiva.admin.domain.events;

@FunctionalInterface
public interface DomainEventPublisher {
    void publishEvent(DomainEvent event);
}
