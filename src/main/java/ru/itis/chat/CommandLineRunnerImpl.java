package ru.itis.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.models.Message;
import ru.itis.chat.models.Room;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.MessageRepository;
import ru.itis.chat.repositories.RoomRepository;
import ru.itis.chat.repositories.UserRepository;
import ru.itis.chat.security.role.Role;
import ru.itis.chat.security.state.State;
import ru.itis.chat.security.state.WebSocketSessionState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        String hashPassword = passwordEncoder.encode("ggwp");
        User user = User.builder()
                .username("Alisher")
                .surname("Alisherov")
                .password(hashPassword)
                .login("ali")
                .role(Role.USER)
                .state(State.ACTIVE)
                .socketSessionState(WebSocketSessionState.NONE)
                .build();

        User user1 = User.builder()
                .username("Dias")
                .surname("Diasov")
                .password( passwordEncoder.encode("ggwp"))
                .login("dias")
                .role(Role.USER)
                .state(State.ACTIVE)
                .socketSessionState(WebSocketSessionState.NONE)
                .build();

        User admin = User.builder()
                .username("admin")
                .surname("adminov")
                .password( passwordEncoder.encode("admin"))
                .login("admin")
                .role(Role.ADMIN)
                .state(State.ACTIVE)
                .socketSessionState(WebSocketSessionState.NONE)
                .build();

        usersRepository.save(user);
        usersRepository.save(user1);
        usersRepository.save(admin);

        Room room1 = Room.builder()
                .name("room1")
                .build();

        Room room2 = Room.builder()
                .name("room2")
                .build();

        Room room3 = Room.builder()
                .name("room3")
                .build();

        Message message1 = Message.builder()
                .text("q")
                .username("dias")
                .room(room1)
                .build();

        Message message2 = Message.builder()
                .text("w")
                .username("ivan")
                .room(room1)
                .build();

        Message message3 = Message.builder()
                .text("e")
                .username("ruslan")
                .room(room1)
                .build();

        Message message4 = Message.builder()
                .text("r")
                .username("alisher")
                .room(room2)
                .build();

        Message message5 = Message.builder()
                .text("t")
                .username("rustem")
                .room(room2)
                .build();

        Message message6 = Message.builder()
                .text("y")
                .username("airat")
                .room(room3)
                .build();

        List<Room> roomList = new ArrayList<>(Arrays.asList(room1, room2, room3));

        List<Message> messageList = new ArrayList<>(Arrays.asList(
                message1,
                message2,
                message3,
                message4,
                message5,
                message6
                ));

        for (Room room: roomList) {
            roomRepository.save(room);
        }

        for (Message message: messageList){
            messageRepository.save(message);
        }



    }
}
