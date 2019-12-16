package ru.itis.chat.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.models.Room;
import ru.itis.chat.repositories.RoomRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoomServiceTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void findAllShouldReturnListRooms(){

        Room room1 = Room.builder()
                .name("room1")
                .build();

        Room room2 = Room.builder()
                .name("room2")
                .build();

        Room room3 = Room.builder()
                .name("room3")
                .build();

        List<Room> roomListExpected = new ArrayList<>(Arrays.asList(room1, room2, room3));

        for (Room room: roomListExpected) {
            roomRepository.save(room);
        }

        List<Room> roomListActual = roomRepository.findAll();

        Assert.assertEquals(roomListExpected, roomListActual);
    }
}
