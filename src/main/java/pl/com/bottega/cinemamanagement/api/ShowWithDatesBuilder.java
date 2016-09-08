package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

/**
 * Created by Dell on 2016-09-08.
 */
public class ShowWithDatesBuilder implements ShowBuilder {

    private Show show;

    @Override
    public void start() {

    }

    @Override
    public void verify() {

    }

    @Override
    public void prepareShow(Cinema cinema, Movie movie) {

    }

    @Override
    public Show end() {
        return null;
    }
}
