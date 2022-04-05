package com.platform.movierama.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.platform.movierama.authentication.UserService;
import com.platform.movierama.domain.Movie;
import com.platform.movierama.domain.MovieUser;
import com.platform.movierama.domain.User;
import com.platform.movierama.repositories.MovieRepository;
import com.platform.movierama.repositories.MovieUserRepository;
import com.platform.movierama.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final MovieRepository movieRepo;
    private final UserRepository userRepo;
    private final MovieUserRepository movieUserRepo;

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest context;

    public MainController(MovieRepository movieRepo, UserRepository userRepo, MovieUserRepository movieUserRepo) {
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
        this.movieUserRepo = movieUserRepo;
    }

    @RequestMapping({"/", "/main"})
    public String getMovies(Model model) {
        checkForLoggedUser(model);
        model.addAttribute("allreviews", movieRepo.findAll());
        return "main";
    }

    @RequestMapping("/sort-by-likes")
    public String sortByLikes(Model model) {
        checkForLoggedUser(model);
        model.addAttribute("allreviews", movieRepo.findAllOrderByLikes());
        return "main";
    }

    @RequestMapping("/sort-by-hates")
    public String sortByHates(Model model) {
        checkForLoggedUser(model);
        model.addAttribute("allreviews", movieRepo.findAllOrderByHates());
        return "main";
    }

    @RequestMapping("/sort-by-date")
    public String sortByDate(Model model) {
        checkForLoggedUser(model);
        model.addAttribute("allreviews", movieRepo.findAllOrderByDate());
        return "main";
    }

    @RequestMapping(value = "/sort-by-user")
    public String sortByUser(@RequestParam(value = "fnameParam") String fname,
                             @RequestParam(value = "lnameParam") String lname,
                             Model model) {
        checkForLoggedUser(model);
        model.addAttribute("allreviews", movieRepo.findAllByName(fname, lname));
        return "main";
    }

    @GetMapping("/signup")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String registrationPost(@ModelAttribute("user") User user, Model model) {
        System.out.println(user.toString());

        String message = userService.save(user);
        model.addAttribute("allreviews", movieRepo.findAll());
        model.addAttribute("signupmessage", message);
        return "main";
    }

    @RequestMapping("/new-movie")
    public String addNewMovie(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "new-movie";
    }

    @PostMapping("/new-movie")
    public String addNewMoviePost(@ModelAttribute("movie") Movie movie, Model model) {
        System.out.println(movie.toString());
        Date date = new Date();
        movie.setDate(date);

        // Since we are here, someone is already registered.
        User user = userRepo.getUserByUsername(context.getRemoteUser());
        user.getMovies().add(movie);
        movie.setUser(user);
        movieRepo.save(movie);

        checkForLoggedUser(model);
        model.addAttribute("allreviews", movieRepo.findAll());
        return "main";
    }

    @RequestMapping(value = "/like")
    public String likeMovie(@RequestParam(value = "idParam") Long movieId, Model model) {
        /**
         * If someone tries to like a post:
         * - If the post belongs to him, don't do anything.
         * - If he liked it once in the past, revert the vote by decreasing the likes by 1.
         * - If he hated it once in the past, don't do anything.
         * - If he vote for the 1st time, increase the likes by 1.
         * - Save all the above actions.
         */

        Optional<Movie> movie = movieRepo.findById(movieId);
        User loggedUser = userRepo.getUserByUsername(context.getRemoteUser());
        MovieUser association = movieUserRepo.findAllPerMovieUser(loggedUser.getId(), movie.get().getId());
        if(association == null) {
            MovieUser firstAssociation = new MovieUser();
            firstAssociation.setMovie(movie.get());
            firstAssociation.setUser(loggedUser);
            firstAssociation.setIsliked(false);
            firstAssociation.setIshated(false);
            loggedUser.getVotes().add(firstAssociation);
            movie.get().getVotes().add(firstAssociation);

            movieUserRepo.save(firstAssociation);
            association = movieUserRepo.findAllPerMovieUser(loggedUser.getId(), movie.get().getId());
        }

        boolean reviewBelongsToUser = loggedUser.getMovies().contains(movie.get());
        boolean isAlreadyLiked = association.isIsliked();

        if(!reviewBelongsToUser) {
            if(isAlreadyLiked) {
                association.setIsliked(false);
                movie.get().setLikes(movie.get().getLikes() - 1);
            } else {
                association.setIsliked(true);
                movie.get().setLikes(movie.get().getLikes() + 1);
            }

            movieUserRepo.save(association);
            movieRepo.save(movie.get());
        }

        checkForLoggedUser(model);
        model.addAttribute("allreviews", movieRepo.findAll());
        return "main";
    }

    @RequestMapping(value = "/hate")
    public String hateMovie(@RequestParam(value = "idParam") Long movieId, Model model) {
        /**
         * If someone tries to hate a post:
         * - If the post belongs to him, don't do anything.
         * - If he hated it once in the past, revert the vote by decreasing the hates by 1.
         * - If he liked it once in the past, don't do anything.
         * - If he vote for the 1st time, increase the hates by 1.
         * - Save all the above actions.
         */

        Optional<Movie> movie = movieRepo.findById(movieId);
        User loggedUser = userRepo.getUserByUsername(context.getRemoteUser());
        MovieUser association = movieUserRepo.findAllPerMovieUser(loggedUser.getId(), movie.get().getId());
        if(association == null) {
            MovieUser firstAssociation = new MovieUser();
            firstAssociation.setMovie(movie.get());
            firstAssociation.setUser(loggedUser);
            firstAssociation.setIsliked(false);
            firstAssociation.setIshated(false);
            loggedUser.getVotes().add(firstAssociation);
            movie.get().getVotes().add(firstAssociation);

            movieUserRepo.save(firstAssociation);
            association = movieUserRepo.findAllPerMovieUser(loggedUser.getId(), movie.get().getId());
        }

        boolean reviewBelongsToUser = loggedUser.getMovies().contains(movie.get());
        boolean isAlreadyHated = association.isIshated();

        if(!reviewBelongsToUser) {
            if(isAlreadyHated) {
                association.setIshated(false);
                movie.get().setHates(movie.get().getHates() - 1);
            } else {
                association.setIshated(true);
                movie.get().setHates(movie.get().getHates() + 1);
            }

            movieUserRepo.save(association);
            movieRepo.save(movie.get());
        }

        checkForLoggedUser(model);
        model.addAttribute("allreviews", movieRepo.findAll());
        return "main";
    }

    private void checkForLoggedUser(Model model) {
        if(context.getRemoteUser() != null){
            User user = userRepo.getUserByUsername(context.getRemoteUser());
            model.addAttribute("fname", user.getFirst_name());
            model.addAttribute("lname", user.getLast_name());
        }
    }
}