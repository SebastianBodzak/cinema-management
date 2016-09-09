package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

import java.util.List;

/**
 * Created by Dell on 2016-09-09.
 */
public interface ShowPreparationStrategy {

    void validate(ShowDto request);

    List<Show> prepare(Cinema cinema, Movie movie, ShowDto request);
}
