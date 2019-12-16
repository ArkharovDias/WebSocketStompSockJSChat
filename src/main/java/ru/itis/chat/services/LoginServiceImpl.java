package ru.itis.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.dto.LoginPasswordDto;
import ru.itis.chat.models.Token;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.TokensRepository;
import ru.itis.chat.repositories.UsersRepository;
import ru.itis.chat.security.tokentype.TokenType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Map<String, String> login(LoginPasswordDto loginPasswordDto) {

        Map<String, String> tokenMap = new HashMap<>();

        Optional<User> userCandidate = usersRepository.findOneByLogin(loginPasswordDto.getLogin());

        if (userCandidate.isPresent()){

            User user = userCandidate.get();

            if (passwordEncoder.matches(loginPasswordDto.getPassword(), user.getPassword())){

                Optional<Token> userSimpleToken = Optional.ofNullable(user.getToken());
                if (!userSimpleToken.isPresent()){

                    Token token = Token.builder()
                            .user(user)
                            .tokenType(TokenType.SIMPLE)
                            .value(UUID.randomUUID().toString())
                            .build();

                    tokensRepository.save(token);

                    tokenMap.put("simpleToken", token.getValue());

                }else{

                    tokenMap.put("simpleToken", userSimpleToken.get().getValue());
                }

            }else {
                throw new IllegalArgumentException("Incorrect password!");
            }
        }else {
            throw new IllegalArgumentException("User not Found!");
        }

        return tokenMap;
    }

}
