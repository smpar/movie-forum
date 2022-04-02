package com.platform.movierama.repositories;

import com.platform.movierama.domain.Movie;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long>{
}
