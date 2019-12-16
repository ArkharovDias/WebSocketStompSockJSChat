package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chat.dto.ChatMessageDto;
import ru.itis.chat.models.Message;
import ru.itis.chat.models.Room;
import ru.itis.chat.services.MessageService;
import ru.itis.chat.services.RoomService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RoomService roomService;

    @MessageMapping("/room/{roomName}")
    public ChatMessageDto handle(@DestinationVariable String roomName, @Payload ChatMessageDto chatMessageDto, Authentication authentication){

        Room room = roomService.findByName(roomName);

        Message message = Message.builder()
                .text(chatMessageDto.getText())
                .username((String) authentication.getPrincipal())
                .room(room)
                .build();

        messageService.save(message);

        chatMessageDto.setUsername((String) authentication.getPrincipal());

        return chatMessageDto;
    }

    @MessageMapping("/admin/{roomName}")
    @SendTo("/topic/room/room1")
    public ChatMessageDto say(@DestinationVariable String roomName, @Payload ChatMessageDto chatMessageDto, Principal principal){
        return chatMessageDto;
    }

    @SubscribeMapping("/room/{roomName}")
    public List<ChatMessageDto> subscribeRoom(@DestinationVariable String roomName){
        return ChatMessageDto.from(messageService.findAllMessagesByRoomName(roomName));
    }

    @MessageExceptionHandler
    @SendToUser(destinations="/queue/errors", broadcast=false)
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
