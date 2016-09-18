package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TicketPrice> ticketPrices;

    public Movie() {
    }

    public Movie(String title, String description, Collection<String> actors, Collection<String> genres, Integer minAge, Integer length) {
        this.actors = actors;
        this.description = description;
        this.genres = genres;
        this.length = length;
        this.minAge = minAge;
        this.title = title;
    }

    public Collection<String> getActors() {
        return actors;
    }

    public void setActors(Collection<String> actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<String> getGenres() {
        return genres;
    }

    public void setGenres(Collection<String> genres) {
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void updatePrices(Set<TicketPrice> ticketPricesSet){
        this.ticketPrices = ticketPricesSet;
    }
}
