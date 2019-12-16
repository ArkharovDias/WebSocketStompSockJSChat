package ru.itis.chat.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.models.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void saveRoomShouldSaveRoom(){

        Room roomExpected = Room.builder()
                .id(1L)
                .name("myChat")
                .build();

        roomRepository.save(roomExpected);

        Room roomActual = roomRepository.findById(1L).get();

        Assert.assertEquals(roomExpected, roomActual);
    }

    @Test
    public void findRoomByNameShouldFindRoomByName(){

        String name = "chat";

        Room roomExpected = Room.builder()
                .name(name)
                .build();

        roomRepository.save(roomExpected);

        Room roomActual = roomRepository.findRoomByName(name);

        Assert.assertEquals(roomExpected, roomActual);

    }

    @Test
    public void findAllRoomsShouldReturnListOfAllRooms(){

        Room room1 = Room.builder()
                .name("chat1")
                .build();

        Room room2 = Room.builder()
                .name("chat2")
                .build();

        Room room3 = Room.builder()
                .name("chat3")
                .build();

        List<Room> roomListExpected = new ArrayList<>(Arrays.asList(room1, room2, room3));

        for (Room room: roomListExpected) {
            roomRepository.save(room);
        }

        List<Room> roomListActual = roomRepository.findAll();

        Assert.assertEquals(roomListExpected, roomListActual);
    }

}
