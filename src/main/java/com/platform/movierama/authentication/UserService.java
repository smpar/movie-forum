package com.platform.movierama.authentication;

import com.platform.movierama.domain.User;

public interface UserService {
    String save(User user);

    User findByUsername(String username);
}