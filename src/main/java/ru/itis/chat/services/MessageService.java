package ru.itis.chat.services;

import ru.itis.chat.models.Message;

import java.util.List;

public interface MessageService {

    void save(Message message);

    List<Message> findAllMessagesByRoomName(String room);
}
