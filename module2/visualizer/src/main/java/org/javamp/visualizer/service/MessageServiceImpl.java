package org.javamp.visualizer.service;

import lombok.AllArgsConstructor;
import org.javamp.visualizer.data.dao.MessageEntity;
import org.javamp.visualizer.data.dto.MessageDto;
import org.javamp.visualizer.mapper.MessageDtoMapper;
import org.javamp.visualizer.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageDtoMapper messageDtoMapper;

    @Override
    public List<MessageDto> getMessages() {
        List<MessageEntity> messages = messageRepository.findAll();

        return messages
                .stream()
                .map(messageDtoMapper::map)
                .toList();
    }
}
