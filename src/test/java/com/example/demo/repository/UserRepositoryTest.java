package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void itShouldCheckWhenUserEmailExists() {
        String email = "thanhhuynhduy@gmail.com";
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setUsername("thanhhuynh");
        user.setPassword("duythanh");
        userRepository.save(user);
        boolean expected = userRepository.selectExistsEmail(email);
        assertThat(expected).isTrue();
    }
}
