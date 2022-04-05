package com.platform.movierama.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class MovieUser {
    @EmbeddedId
    MovieUserKey id = new MovieUserKey();

    @ManyToOne
    @MapsId("movieId")
    private Movie movie;

    @ManyToOne
    @MapsId("userId")
    private User user;

    private boolean isliked;
    private boolean ishated;

    public MovieUser() {
    }

    public MovieUser(MovieUserKey id, Movie movie, User user, boolean isliked, boolean ishated) {
        this.id = id;
        this.movie = movie;
        this.user = user;
        this.isliked = isliked;
        this.ishated = ishated;
    }

    public MovieUserKey getId() {
        return id;
    }

    public void setId(MovieUserKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }

    public boolean isIshated() {
        return ishated;
    }

    public void setIshated(boolean ishated) {
        this.ishated = ishated;
    }

    @Override
    public String toString() {
        return "MovieUser [id=" + id + ", ishated=" + ishated + ", isliked=" + isliked + ", movie=" + movie + ", user="
                + user + "]";
    }
}