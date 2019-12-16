package ru.itis.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.chat.models.Message;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private String username;
    private String text;

    public static ChatMessageDto from(Message message){
        return ChatMessageDto.builder()
                .username(message.getUsername())
                .text(message.getText())
                .build();
    }

    public static List<ChatMessageDto> from(List<Message> messages){
        return messages.stream()
                .map(ChatMessageDto::from)
                .collect(Collectors.toList());
    }
}
