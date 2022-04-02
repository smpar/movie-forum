package com.platform.movierama;

import java.util.Date;

import com.platform.movierama.domain.Movie;
import com.platform.movierama.repositories.MovieRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepo;

    @BeforeEach
    public void setup() {
        movieRepo.deleteAll();
    }

    @Test public void checkInsertedMovies() {
        // save a few movies
        Date date = new Date();
        movieRepo.save(new Movie("The Emprire Strikes Back", "Lorem ipsum",
                date, 0, 0));
        movieRepo.save(new Movie("Prometheus", "dolor sit",
                date, 0, 0));

        // fetch all movies
        for(Movie movie : movieRepo.findAll()) {
            System.out.println(movie.toString());
        }
    }
}
