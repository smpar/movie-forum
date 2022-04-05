package com.platform.movierama.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private Date date;
    private int likes;
    private int hates;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "movie")
    Set<MovieUser> votes = new HashSet<>();

    public Movie(String title, String description, Date date, int likes, int hates) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.likes = likes;
        this.hates = hates;
    }

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getHates() {
        return hates;
    }

    public void setHates(int hates) {
        this.hates = hates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<MovieUser> getVotes() {
        return votes;
    }

    public void setVotes(Set<MovieUser> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Movie [date=" + date + ", description=" + description + ", hates=" + hates + ", id=" + id + ", likes="
                + likes + ", title=" + title + "]";
    }
}
