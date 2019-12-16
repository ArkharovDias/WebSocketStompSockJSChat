package ru.itis.chat.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.models.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void saveMessageShouldSaveMessage(){

        Message messageExpected = Message.builder()
                .id(1L)
                .text("hello")
                .build();

        messageRepository.save(messageExpected);

        Message messageActual = messageRepository.findById(1L).get();

        Assert.assertEquals(messageExpected, messageActual);

    }

    @Test
    public void findAllMessagesShouldFindAllMessages(){

        Message message1 = Message.builder()
                .id(1L)
                .text("hello")
                .build();

        Message message2 = Message.builder()
                .id(2L)
                .text("Bye")
                .build();

        Message message3 = Message.builder()
                .id(3L)
                .text("gg")
                .build();

        List<Message> messageListExpected = new ArrayList<>(Arrays.asList(message1, message2, message3));

        for (Message message: messageListExpected) {
            messageRepository.save(message);
        }

        List<Message> messageListActual = messageRepository.findAll();

        Assert.assertEquals(messageListExpected, messageListActual);

    }


}
