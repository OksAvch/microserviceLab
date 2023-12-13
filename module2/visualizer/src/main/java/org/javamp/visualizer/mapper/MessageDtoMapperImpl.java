package org.javamp.visualizer.mapper;

import org.javamp.visualizer.data.dao.MessageEntity;
import org.javamp.visualizer.data.dto.MessageDto;
import org.springframework.stereotype.Component;

@Component
public class MessageDtoMapperImpl implements MessageDtoMapper {
    @Override
    public MessageDto map(MessageEntity entity) {
        return MessageDto.builder()
                .id(entity.getId())
                .message(entity.getMessage())
                .build();
    }
}
