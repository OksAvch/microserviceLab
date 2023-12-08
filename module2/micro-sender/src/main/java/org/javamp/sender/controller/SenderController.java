package org.javamp.sender.controller;

import org.javamp.sender.dto.MessageDto;

public interface SenderController {

    void sendNotification(MessageDto message);
}
