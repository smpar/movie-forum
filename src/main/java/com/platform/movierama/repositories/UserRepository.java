package com.platform.movierama.repositories;

import com.platform.movierama.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
