package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

import java.util.Collection;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateShowRequest {

    private Long movieId;
    private Collection<String> dates;
    private CalendarDto calendar;

    public Show validate(Cinema cinema, Movie movie) {
        if (checkIfThereAreAllRequiredParameters())
            throw new InvalidRequestException("movieId with dates or with calendar are required");
        if (checkIfThereAreToManyParameters())
            throw new InvalidRequestException("can not put dates and calendar at the same time");
        else {
            return prepareShow(cinema, movie);
        }
    }

    private Show prepareShow(Cinema cinema, Movie movie) {
        ShowBuilder showBuilder;
        if (dates != null)
            showBuilder = new ShowWithDatesBuilder();
        else
            showBuilder = new ShowWithCalendarBuilder();
        showBuilder.start();
        showBuilder.verify();
        showBuilder.prepareShow(cinema, movie);
        return showBuilder.end();
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
}
