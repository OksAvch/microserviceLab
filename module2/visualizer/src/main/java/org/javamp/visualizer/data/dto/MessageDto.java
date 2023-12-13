package org.javamp.visualizer.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {

    private long id;
    private String message;
}
