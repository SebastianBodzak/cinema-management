package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

/**
 * Created by Dell on 2016-09-08.
 */
public interface ShowBuilder {

    void start();

    void verify();

    void prepareShow(Cinema cinema, Movie movie);

    Show end();
}
