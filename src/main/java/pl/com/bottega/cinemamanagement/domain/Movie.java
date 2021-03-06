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
    private Set<String> actors;

    @ElementCollection
    private Set<String> genres;

    private Integer minAge;
    private Integer length;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", fetch = FetchType.EAGER)
    @OrderBy("time")
    private Set<Show> shows;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TicketPrice> ticketPrices;

    public Movie() {
    }

    public Movie(String title, String description, Set<String> actors, Set<String> genres, Integer minAge, Integer length) {
        this.actors = actors;
        this.description = description;
        this.genres = genres;
        this.length = length;
        this.minAge = minAge;
        this.title = title;
    }

    public Set<Show> getShows() {
        return shows;
    }

    public Collection<String> getActors() {
        return actors;
    }

    public String getDescription() {
        return description;
    }

    public Collection<String> getGenres() {
        return genres;
    }

    public Long getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public int getMinAge() {
        return minAge;
    }

    public String getTitle() {
        return title;
    }

    public void updatePrices(Set<TicketPrice> ticketPricesSet) {
//        ticketPrices.clear();
//        ticketPrices.add(ticketPricesSet);
        this.ticketPrices = ticketPricesSet;
    }

    public Set<TicketPrice> getTicketPrices() {
        return ticketPrices;
    }
}
