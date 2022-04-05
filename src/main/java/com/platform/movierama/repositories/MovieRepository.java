package com.platform.movierama.repositories;

import com.platform.movierama.domain.Movie;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m ORDER BY m.likes DESC")
    Iterable<Movie> findAllOrderByLikes();

    @Query("SELECT m FROM Movie m ORDER BY m.hates DESC")
    Iterable<Movie> findAllOrderByHates();

    @Query("SELECT m FROM Movie m ORDER BY m.date DESC")
    Iterable<Movie> findAllOrderByDate();

    @Query(value = "SELECT * FROM Movie m INNER JOIN User u ON m.user_id = u.id "+
                    "WHERE u.first_name = ?1 AND u.last_name = ?2", nativeQuery = true)
    Iterable<Movie> findAllByName(String fname, String lname);
}