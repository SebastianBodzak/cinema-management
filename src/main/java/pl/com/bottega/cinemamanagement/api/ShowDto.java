package pl.com.bottega.cinemamanagement.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * Created by Dell on 2016-09-09.
 */
public class ShowDto {

    private Long movieId;
    private Collection<String> dates;
    private CalendarDto calendar;

    public void validate() {
        if (checkIfThereAreAllRequiredParameters())
            throw new InvalidRequestException("movieId with dates or movieId with calendar are required");
        if (checkIfThereAreToManyParameters())
            throw new InvalidRequestException("can not put dates and calendar at the same time");
        if (!(dates == null)) {
            checkIfDatesAreEmptyCollection();
            checkIfDatesAreValid();
        }
        else
            calendar.validate();
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

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setDates(Collection<String> dates) {
        this.dates = dates;
    }

    public void setCalendar(CalendarDto calendar) {
        this.calendar = calendar;
    }

    private void checkIfDatesAreValid() throws InvalidRequestException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        for (String date : dates) {
            try {
                LocalDate.parse(date, formatter);
//                checkAdditionalDates(date);
            }
            catch (Exception ex) {
                throw new InvalidRequestException("Invalid date format");
            }
        }
    }

    private void checkAdditionalDates(String date) {
        if (date == "yyyy/MM/dd HH:mm")
            throw new IllegalArgumentException();
    }

    private void checkIfDatesAreEmptyCollection() throws InvalidRequestException {
        if (dates.isEmpty())
            throw new InvalidRequestException("Dates are required");
    }
}
