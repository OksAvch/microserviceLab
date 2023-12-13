package org.javamp.visualizer.service;

import org.javamp.visualizer.data.dto.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getMessages();
}
