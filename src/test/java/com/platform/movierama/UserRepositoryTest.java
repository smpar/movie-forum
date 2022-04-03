package com.platform.movierama;

import com.platform.movierama.domain.User;
import com.platform.movierama.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;

    @BeforeEach
    public void setup() {
        userRepo.deleteAll();
    }

    @Test
    public void checkUserSavedProperly() {
        userRepo.save(new User("First", "Last", "firstlast"));
        assertThat(userRepo.count()).isEqualTo(1);
    }

    @Test public void checkInsertedUsers() {
        userRepo.save(new User("Jaime", "Lannister", "jaimelannister"));
        userRepo.save(new User("Theon", "Greyjoy", "theongreyjoy"));

        // fetch all users
        for(User user : userRepo.findAll()) {
            System.out.println(user.toString());
        }
    }
    
}
