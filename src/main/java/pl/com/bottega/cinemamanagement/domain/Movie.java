package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

/**
 * Created by ulvar on 04.09.2016.
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @ElementCollection
    private Collection<String> actors;

    @ElementCollection
    private Collection<String> genres;

    private Integer minAge;
    private Integer length;

    public Movie(){}

    public Movie(String title, String description, Collection<String> actors, Collection<String> genres,Integer minAge, Integer length) {
        this.actors = actors;
        this.description = description;
        this.genres = genres;
        this.length = length;
        this.minAge = minAge;
        this.title = title;
    }
}
