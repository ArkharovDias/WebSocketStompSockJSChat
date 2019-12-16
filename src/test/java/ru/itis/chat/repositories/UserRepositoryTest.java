package ru.itis.chat.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.models.User;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser_ShouldSaveUser(){

        User userExpected = User.builder()
                .username("dias")
                .surname("arkharov")
                .login("dias")
                .password("ggwp")
                .build();

        userRepository.save(userExpected);

        User userActual = userRepository.findById(1L).get();

        Assert.assertEquals(userExpected, userActual);
    }

    @Test
    public void findUserByLogin_ShouldFindUserByLogin(){

        String login = "dias";

        User userExpected = User.builder()
                .username("dias")
                .surname("arkharov")
                .login(login)
                .password("ggwp")
                .build();

        userRepository.save(userExpected);

        User userActual = userRepository.findUserByLogin(login).get();

        Assert.assertEquals(userExpected, userActual);

    }

}
