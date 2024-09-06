package org.javamp.visualizer.controller;

import org.javamp.visualizer.data.dto.MessageDto;

import java.util.List;

public interface MessagesController {

    List<MessageDto> getMessages();
}
