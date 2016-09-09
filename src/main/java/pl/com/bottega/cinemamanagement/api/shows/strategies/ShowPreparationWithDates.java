package pl.com.bottega.cinemamanagement.api.shows.strategies;

import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.ShowDto;
import pl.com.bottega.cinemamanagement.api.ShowPreparationStrategy;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.ShowsFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        List<Date> dates = parseStringsToDates(request.getDates());
        ShowsFactory showsFactory = new ShowsFactory();
        return showsFactory.createShows(cinema, movie, dates);
    }

    private void checkIfDatesAreValid(Collection<String> dates) throws InvalidRequestException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        for (String date : dates) {
            try {
                LocalDate localDate = LocalDate.parse(date, formatter);
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

    private List<Date> parseStringsToDates(Collection<String> stringDates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        List<Date> dates = new LinkedList<>();
        for (String s : stringDates) {
            LocalDate localDate = LocalDate.parse(s, formatter);
            dates.add(parseLocalDateToDate(localDate));
        }
        return dates;
    }

    private Date parseLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
