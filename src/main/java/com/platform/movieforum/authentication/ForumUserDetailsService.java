package com.platform.movieforum.authentication;

import com.platform.movieforum.domain.User;
import com.platform.movieforum.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ForumUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // This is needed for the 1st time a user clicks the "Log in" option.
        if (username.isEmpty())
            throw new UsernameNotFoundException("username is empty!");

        User user =  userRepository.getUserByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("username not found!");

        return new ForumUserDetails(user);
    }
}