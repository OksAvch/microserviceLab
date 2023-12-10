package org.javamp.recipient.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamp.recipient.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class MessageControllerImpl implements MessageController {
    private final MessageService service;

    @Override
    @GetMapping("/messages")
    public List<String> getMessages() {
        log.info("Request to provide messages was received");
        return service.getMessages();
    }
}
