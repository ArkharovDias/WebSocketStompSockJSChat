package ru.itis.chat.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import ru.itis.chat.models.User;
import ru.itis.chat.security.state.WebSocketSessionState;
import ru.itis.chat.services.UsersService;

import java.util.Map;

@Component
public class AuthHandshakeHandler implements HandshakeHandler {

    @Autowired
    private UsersService usersService;

    @Autowired
    private CustomHandshakeHandler handshakeHandler;

    @Override
    public boolean doHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws HandshakeFailureException {

        String userLogin = serverHttpRequest.getPrincipal().getName();

        User user = usersService.findByLogin(userLogin);

        if (user.getSocketSessionState().equals(WebSocketSessionState.NONE)){
            user.setSocketSessionState(WebSocketSessionState.ACTIVE);
            usersService.save(user);
        }

        if (!user.getSocketSessionState().equals(WebSocketSessionState.BANNED)){
            return handshakeHandler.doHandshake(serverHttpRequest, serverHttpResponse, webSocketHandler, map);
        }else {
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }
    }
}
