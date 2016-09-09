package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

import java.util.List;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateShowRequest {

    private ShowDto shows;

    public void validate() {
        if (shows == null)
            throw new InvalidRequestException("Shows are required");
        shows.validate();
    }

    public List<Show> prepareShows(Cinema cinema, Movie movie) {
        return shows.prepareShow(cinema, movie);
    }

    public ShowDto getShows() {
        return shows;
    }

    public void setShows(ShowDto shows) {
        this.shows = shows;
    }

    public Long getMovieId() {
        return shows.getMovieId();
    }
}
