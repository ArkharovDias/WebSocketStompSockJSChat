package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chat.dto.RoomDto;
import ru.itis.chat.security.authentication.TokenAuthentication;
import ru.itis.chat.services.RoomService;

import java.util.Arrays;
import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/rooms")
    public ResponseEntity<List<RoomDto>> getAllRooms(){
        TokenAuthentication authentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        System.out.println((String)authentication.getPrincipal());
        System.out.println(authentication.isAuthenticated());
        return ResponseEntity.ok(RoomDto.from(roomService.findAllRooms()));
    }

}
