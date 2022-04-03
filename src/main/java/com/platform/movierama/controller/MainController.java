package com.platform.movierama.controller;

import com.platform.movierama.repositories.MovieRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final MovieRepository movieRepo;

    public MainController(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @RequestMapping({"/", "/main"})
    public String getMovies(Model model) {
        model.addAttribute("allreviews", movieRepo.findAll());
        return "main";
    }

    @RequestMapping("/sort-by-likes")
    public String sortByLikes(Model model) {
        model.addAttribute("allreviews", movieRepo.findAllOrderByLikes());
        return "main";
    }

    @RequestMapping("/sort-by-hates")
    public String sortByHates(Model model) {
        model.addAttribute("allreviews", movieRepo.findAllOrderByHates());
        return "main";
    }

    @RequestMapping("/sort-by-date")
    public String sortByDate(Model model) {
        model.addAttribute("allreviews", movieRepo.findAllOrderByDate());
        return "main";
    }

    @RequestMapping(value = "/sort-by-user")
    public String sortByUser(@RequestParam(value = "fnameParam") String fname,
                             @RequestParam(value = "lnameParam") String lname,
                             Model model) {
        model.addAttribute("allreviews", movieRepo.findAllByName(fname, lname));
        return "main";
    }

    @RequestMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }
}