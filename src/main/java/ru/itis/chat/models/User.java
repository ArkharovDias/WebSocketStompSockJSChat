package ru.itis.chat.models;

import lombok.*;
import ru.itis.chat.security.role.Role;
import ru.itis.chat.security.state.State;
import ru.itis.chat.security.state.WebSocketSessionState;
import sun.misc.resources.Messages;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String surname;
    private String login;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Token token;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Room> rooms;

    @Enumerated(value = EnumType.STRING)
    private WebSocketSessionState socketSessionState;

}
