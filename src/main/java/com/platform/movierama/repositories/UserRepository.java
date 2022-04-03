package com.platform.movierama.repositories;

import com.platform.movierama.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM User u WHERE u.username = ?1", nativeQuery = true)
    public User getUserByUsername(String fname);
}
