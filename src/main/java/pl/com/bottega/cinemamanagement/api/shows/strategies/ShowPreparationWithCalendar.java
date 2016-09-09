package pl.com.bottega.cinemamanagement.api.shows.strategies;

import pl.com.bottega.cinemamanagement.api.ShowDto;
import pl.com.bottega.cinemamanagement.api.ShowPreparationStrategy;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

import java.util.List;

/**
 * Created by Dell on 2016-09-09.
 */
public class ShowPreparationWithCalendar implements ShowPreparationStrategy {

    @Override
    public void validate(ShowDto request) {

    }

    @Override
    public List<Show> prepare(Cinema cinema, Movie movie, ShowDto request) {
        return null;
    }

}
