package ru.itis.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.chat.models.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findRoomByName(String name);
}
