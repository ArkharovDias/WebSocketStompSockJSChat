package ru.itis.chat.services;

import ru.itis.chat.models.User;

import java.util.List;
import java.util.Map;


public interface UsersService {

    List<User> findAll();

    User findOne(Long userId);

    User findByLogin(String login);

    void save(User user);
}
