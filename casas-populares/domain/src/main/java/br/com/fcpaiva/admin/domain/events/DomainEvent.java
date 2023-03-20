package br.com.fcpaiva.admin.domain.events;

import java.io.Serializable;
import java.time.Instant;
public interface DomainEvent extends Serializable {
    Instant occurredOn();
}
