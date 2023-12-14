package org.javamp.collector.service;

import lombok.AllArgsConstructor;
import org.javamp.collector.data.MessageEntity;
import org.javamp.collector.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public void saveMessage(String message) {
        MessageEntity messageEntities = buildMessageEntity(message);

        messageRepository.save(messageEntities);
    }

    private MessageEntity buildMessageEntity(String message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(message);
        return messageEntity;
    }
}
