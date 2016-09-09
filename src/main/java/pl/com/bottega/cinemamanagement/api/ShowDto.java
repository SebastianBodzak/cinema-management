package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.api.shows.strategies.ShowPreparationWithCalendar;
import pl.com.bottega.cinemamanagement.api.shows.strategies.ShowPreparationWithDates;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

import java.util.Collection;
import java.util.List;

/**
 * Created by Dell on 2016-09-09.
 */
public class ShowDto {

    private Long movieId;
    private Collection<String> dates;
    private CalendarDto calendar;

    public void validate() {
        if (checkIfThereAreAllRequiredParameters())
            throw new InvalidRequestException("movieId with dates or with calendar are required");
        if (checkIfThereAreToManyParameters())
            throw new InvalidRequestException("can not put dates and calendar at the same time");
        else
            chooseStrategy().validate(this);
    }

    public List<Show> prepareShow(Cinema cinema, Movie movie) {
        return chooseStrategy().prepare(cinema, movie, this);
    }

    private boolean checkIfThereAreToManyParameters() {
        return dates != null && calendar != null;
    }

    private boolean checkIfThereAreAllRequiredParameters() {
        return movieId == null || (dates == null && calendar == null);
    }

    public Long getMovieId() {
        return movieId;
    }

    public Collection<String> getDates() {
        return dates;
    }

    public CalendarDto getCalendar() {
        return calendar;
    }

    private ShowPreparationStrategy chooseStrategy() {
        ShowPreparationStrategy strategy;
        if (dates != null)
            return strategy = new ShowPreparationWithDates();
        else
            return strategy = new ShowPreparationWithCalendar();
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setDates(Collection<String> dates) {
        this.dates = dates;
    }

    public void setCalendar(CalendarDto calendar) {
        this.calendar = calendar;
    }
}
