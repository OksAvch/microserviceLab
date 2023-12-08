package org.javamp.sender.service;

import org.javamp.sender.dto.MessageDto;

public interface SenderService {
    void sendNotification(MessageDto message);
}
