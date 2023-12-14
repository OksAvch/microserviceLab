package org.javamp.recipient.service;

public interface MessageService {
    String getMessage();

    void pulMessages(String queue);
}
