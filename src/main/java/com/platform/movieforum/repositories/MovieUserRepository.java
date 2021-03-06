package com.platform.movieforum.repositories;

import com.platform.movieforum.domain.MovieUser;
import com.platform.movieforum.domain.MovieUserKey;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieUserRepository extends CrudRepository<MovieUser, MovieUserKey> {
     // ISHATED | ISLIKED | USER_ID | MOVIE_ID
    @Query(value = "SELECT * FROM Movie_User mu WHERE mu.USER_ID = ?1 AND mu.MOVIE_ID = ?2", nativeQuery = true)
    MovieUser findAllPerMovieUser(Long userId, Long movieId);
}
