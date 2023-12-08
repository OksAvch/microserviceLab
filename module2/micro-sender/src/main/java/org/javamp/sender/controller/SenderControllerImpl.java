package org.javamp.sender.controller;

import lombok.AllArgsConstructor;
import org.javamp.sender.dto.MessageDto;
import org.javamp.sender.service.SenderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SenderControllerImpl implements SenderController {
    private SenderService service;

    @Override
    @PostMapping("/notification")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendNotification(@RequestBody MessageDto message) {
        service.sendNotification(message);
    }
}
