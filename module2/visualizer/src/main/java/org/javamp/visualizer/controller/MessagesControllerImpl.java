package org.javamp.visualizer.controller;

import lombok.AllArgsConstructor;
import org.javamp.visualizer.data.dto.MessageDto;
import org.javamp.visualizer.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MessagesControllerImpl implements MessagesController {
    private final MessageService messageService;

    @Override
    @GetMapping(path = "/saved-messages")
    public List<MessageDto> getMessages() {
        return messageService.getMessages();
    }
}
