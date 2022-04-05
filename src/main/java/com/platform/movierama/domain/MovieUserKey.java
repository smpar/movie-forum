package com.platform.movierama.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MovieUserKey implements Serializable {
    private Long movieId;
    private Long userId;

    public MovieUserKey() {
    }

    public MovieUserKey(Long movieId, Long userId) {
        this.movieId = movieId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MovieUserKey [movieId=" + movieId + ", userId=" + userId + "]";
    }
}