package com.platform.movierama.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String first_name;
    private String last_name;
    private String username;
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")   // Foreing key relationship
    Set<Movie> movies = new HashSet<>();

    @OneToMany(mappedBy = "user")
    Set<MovieUser> votes = new HashSet<>();

    public User(String first_name, String last_name, String username) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
    }

    public User() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<MovieUser> getVotes() {
        return votes;
    }

    public void setVotes(Set<MovieUser> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "User [first_name=" + first_name + ", id=" + id + ", last_name=" + last_name + ", username=" + username
                + "]";
    }
}
