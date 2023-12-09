package org.javamp.recipient.service;

import java.util.List;

public interface MessageService {
    List<String> getMessages();

    void pulMessages(String queue);
}
