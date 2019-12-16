package ru.itis.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.chat.models.Room;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private String name;

    public static RoomDto from(Room room){
        return RoomDto.builder()
                .name(room.getName())
                .build();
    }

    public static List<RoomDto> from(List<Room> rooms){
        return rooms.stream()
                .map(RoomDto::from)
                .collect(Collectors.toList());
    }
}
