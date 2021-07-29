package com.example.demo.repository;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@EntityScan({"com.example.demo.entity"})
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUsernameTest() {
        Optional<User> test = userRepository.findByUsername("test22");
        assertTrue(test.isPresent());
    }

}