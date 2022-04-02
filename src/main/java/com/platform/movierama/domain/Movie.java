package com.platform.movierama.domain;

import java.util.Date;

public class Movie {

    private String title;
    private String description;
    private Date date;
    private int likes;
    private int hates;

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

    @Override
    public String toString() {
        return "Movie [date=" + date + ", description=" + description + ", hates=" + hates + ", likes=" + likes
                + ", title=" + title + "]";
    }
}
