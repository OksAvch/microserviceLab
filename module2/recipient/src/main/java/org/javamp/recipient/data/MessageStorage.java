package org.javamp.recipient.data;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@AllArgsConstructor
public class MessageStorage {
    private static final LinkedList<String> MESSAGE_STORAGE = new LinkedList<>();

    public String pull() {
        return MESSAGE_STORAGE.pollFirst();
    }

    public void store(String message) {
        MESSAGE_STORAGE.add(message);
    }
}
