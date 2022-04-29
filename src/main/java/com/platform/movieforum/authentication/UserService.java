package com.platform.movieforum.authentication;

import com.platform.movieforum.domain.User;

public interface UserService {
    String save(User user);

    User findByUsername(String username);
}