package pl.com.bottega.cinemamanagement.api.shows.strategies;

import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.ShowDto;
import pl.com.bottega.cinemamanagement.api.ShowPreparationStrategy;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.ShowsFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dell on 2016-09-09.
 */
public class ShowPreparationWithDates implements ShowPreparationStrategy {

    @Override
    public void validate(ShowDto request) throws InvalidRequestException {
        checkIDatesAreEmptyCollection(request.getDates());
        checkIfDatesAreValid(request.getDates());
    }

    @Override
    public List<Show> prepare(Cinema cinema, Movie movie, ShowDto request) {
        List<LocalDateTime> dates = parseStringsToDates(request.getDates());
        return new ShowsFactory().createShows(cinema, movie, dates);
    }

    private void checkIfDatesAreValid(Collection<String> dates) throws InvalidRequestException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        for (String date : dates) {
            try {
                LocalDate.parse(date, formatter);
            }
            catch (Exception ex) {
                throw new InvalidRequestException("Invalid date format");
            }
        }
    }

    private void checkIDatesAreEmptyCollection(Collection<String> dates) throws InvalidRequestException {
        if (dates.isEmpty())
            throw new InvalidRequestException("Dates are required");
    }

    private List<LocalDateTime> parseStringsToDates(Collection<String> stringDates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        List<LocalDateTime> dates = new LinkedList<>();
        for (String s : stringDates)
            dates.add(LocalDateTime.parse(s, formatter));
        return dates;
    }
}
