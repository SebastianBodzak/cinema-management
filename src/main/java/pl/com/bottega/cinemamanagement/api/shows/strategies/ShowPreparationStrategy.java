package pl.com.bottega.cinemamanagement.api.shows.strategies;

import pl.com.bottega.cinemamanagement.api.ShowDto;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

import java.util.List;

/**
 * Created by arkadiuszarak on 15/09/2016.
 */
public interface ShowPreparationStrategy {
    void validate(ShowDto request);
    List<Show> prepare(Cinema cinema, Movie movie, ShowDto request);
}
