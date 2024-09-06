package org.javamp.visualizer.mapper;

import org.javamp.visualizer.data.dao.MessageEntity;
import org.javamp.visualizer.data.dto.MessageDto;

public interface MessageDtoMapper {
    MessageDto map(MessageEntity entity);
}
