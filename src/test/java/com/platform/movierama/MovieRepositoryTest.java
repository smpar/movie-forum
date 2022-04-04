package com.platform.movierama;

import java.util.Date;

import com.platform.movierama.domain.Movie;
import com.platform.movierama.domain.User;
import com.platform.movierama.repositories.MovieRepository;
import com.platform.movierama.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private UserRepository userRepo;

    @BeforeEach
    public void setup() {
        movieRepo.deleteAll();
        userRepo.deleteAll();
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

    @Test public void CheckReferenceFromMovieToUser() {
        User u1 = new User("Jaime", "Lannister", "jaimelannister");
        userRepo.save(u1);
        User u2 = new User("Theon", "Greyjoy", "theongreyjoy");
        userRepo.save(u2);
        User u3 = new User("Eddard", "Stark", "eddardstark");
        userRepo.save(u3);

        Date date = new Date();

        Movie m1 = new Movie("The Emprire Strikes Back", "Lorem ipsum", date, 1, 2);
        u1.getMovies().add(m1);
        m1.setUser(u1);
        movieRepo.save(m1);
        userRepo.save(u1);

        Movie m2 = new Movie("Prometheus", "dolor sit", date, 3, 4);
        u2.getMovies().add(m2);
        m2.setUser(u2);
        movieRepo.save(m2);
        userRepo.save(u2);

        Movie m3 = new Movie("Alien", "amet consectetur", date, 5, 6);
        u1.getMovies().add(m3);
        m3.setUser(u1);
        movieRepo.save(m3);
        userRepo.save(u1);

        assertThat(m3.getUser().getFirst_name()).isEqualTo("Jaime");
    }
}
