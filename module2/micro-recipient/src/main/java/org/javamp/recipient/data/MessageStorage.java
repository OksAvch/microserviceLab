package org.javamp.recipient.data;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageStorage {
    private static final List<String> MESSAGE_STORAGE = new ArrayList<>();

    public List<String> pull() {
        List<String> messagesState = new ArrayList<>(MESSAGE_STORAGE);
        MESSAGE_STORAGE.clear();
        return messagesState;
    }

    public void store(String message) {
        MESSAGE_STORAGE.add(message);
    }
}
