package br.com.fcpaiva.admin.domain.exceptions;


import br.com.fcpaiva.admin.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
