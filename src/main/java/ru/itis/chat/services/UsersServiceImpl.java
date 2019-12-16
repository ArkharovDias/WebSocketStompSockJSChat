package ru.itis.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.chat.models.Token;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.TokensRepository;
import ru.itis.chat.repositories.UsersRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User findOne(Long userId) {
        return usersRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public User findByLogin(String login) {
        return usersRepository.findOneByLogin(login).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void save(User user) {
        usersRepository.save(user);
    }


}
