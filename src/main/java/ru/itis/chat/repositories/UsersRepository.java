package ru.itis.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.chat.models.User;

import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByUsername(String username);

    Optional<User> findOneByLogin(String login);

    @Query("select user from User user left join user.token token where token.value = ?1")
    Optional<User> findOneByToken(String value);

}
