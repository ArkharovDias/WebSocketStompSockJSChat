package ru.itis.chat.services;

import ru.itis.chat.models.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAllRooms();
    Room findByName(String name);

}
