package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.User;
import ru.itis.chat.security.state.WebSocketSessionState;
import ru.itis.chat.services.UsersService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(UserDto.from(usersService.findAll()));
    }

    @MessageMapping("/user/{userLogin}/disconnect")
    public void disconnect(@DestinationVariable String userLogin){
        User user = usersService.findByLogin(userLogin);
        user.setSocketSessionState(WebSocketSessionState.BANNED);
        usersService.save(user);
    }

    @GetMapping("/user/{userLogin}/connect")
    public ResponseEntity connect(@PathVariable String userLogin){
        User user = usersService.findByLogin(userLogin);
        user.setSocketSessionState(WebSocketSessionState.ACTIVE);
        usersService.save(user);
        return ResponseEntity.ok().build();
    }
}
