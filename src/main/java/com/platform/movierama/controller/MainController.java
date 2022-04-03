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

    @RequestMapping("/reviews")
    public String getMovies(Model model) {
        model.addAttribute("allreviews", movieRepo.findAll());
        return "list";
    }

    @RequestMapping("/sort-by-likes")
    public String sortByLikes(Model model) {
        model.addAttribute("allreviews", movieRepo.findAllOrderByLikes());
        return "list";
    }

    @RequestMapping("/sort-by-hates")
    public String sortByHates(Model model) {
        model.addAttribute("allreviews", movieRepo.findAllOrderByHates());
        return "list";
    }

    @RequestMapping("/sort-by-date")
    public String sortByDate(Model model) {
        model.addAttribute("allreviews", movieRepo.findAllOrderByDate());
        return "list";
    }

    @RequestMapping(value = "/sort-by-user")
    public String sortByhaha(@RequestParam(value = "fnameParam") String fname,
                             @RequestParam(value = "lnameParam") String lname,
                             Model model) {
        model.addAttribute("allreviews", movieRepo.findAllByName(fname, lname));
        return "list";
    }
}