package org.javamp.collector.service;

import lombok.AllArgsConstructor;
import org.javamp.collector.data.MessageEntity;
import org.javamp.collector.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public void saveMessage(List<String> message) {
        List<MessageEntity> messageEntities = buildMessageEntities(message);

        messageRepository.saveAll(messageEntities);
    }

    private List<MessageEntity> buildMessageEntities(List<String> message) {
        return message
                .stream()
                .map(m -> {
                    MessageEntity messageEntity = new MessageEntity();
                    messageEntity.setMessage(m);
                    return messageEntity;
                })
                .toList();
    }
}
