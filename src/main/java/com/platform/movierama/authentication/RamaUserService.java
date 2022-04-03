package com.platform.movierama.authentication;

import com.platform.movierama.domain.User;
import com.platform.movierama.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RamaUserService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String save(User user) {
        if(findByUsername(user.getUsername()) != null) {
            System.out.println("[Warning] User already exists.");
            return "User already exists.";
        }

        User newUser  = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        userRepository.save(newUser);

        return "User successfully added.";
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
