package ru.itis.chat.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

import static org.springframework.messaging.simp.SimpMessageType.*;

@Configuration
public class SocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .nullDestMatcher().authenticated()
                .simpSubscribeDestMatchers("/user/queue/errors").permitAll()
                .simpDestMatchers("/user/*/queue/disconnect").hasAuthority("ADMIN")
                .simpDestMatchers("/app/user/*/disconnect").hasAuthority("ADMIN")
                .simpSubscribeDestMatchers("/user/queue/disconnect").authenticated()
                .simpDestMatchers("/app/room/**").authenticated()
                .simpDestMatchers("/app/admin/**").hasAuthority("ADMIN")
                .simpSubscribeDestMatchers("/topic/room/**").authenticated()
                .simpMessageDestMatchers("/queue/**", "/topic/**").denyAll()
                .anyMessage().denyAll();
                //.simpDestMatchers("/app/room/*").denyAll()
                //.simpMessageDestMatchers("/app/admin/**").denyAll()
                //.simpDestMatchers("/app/admin/**").denyAll()
                //.simpDestMatchers("/app/room/**").hasAuthority("USER")
                //.simpMessageDestMatchers("/app/room/**").hasAuthority("USER")
                //.simpSubscribeDestMatchers("/topic/**").hasAuthority("USER")
                //.nullDestMatcher().authenticated();
                //.anyMessage().authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}