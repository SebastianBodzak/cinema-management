package pl.com.bottega.cinemamanagement.domain;

import java.util.List;

/**
 * Created by bartosz.paszkowski on 04.09.2016.
 */
public class Movie {

    private String title;
    private String description;
    private List<String> actors;
    private List<String> genres;
    private int length;
    private int minAge;

    public Movie(String title, String description, List<String> actors, List<String> genres, int length, int minAge) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.length = length;
        this.minAge = minAge;
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

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }
}
