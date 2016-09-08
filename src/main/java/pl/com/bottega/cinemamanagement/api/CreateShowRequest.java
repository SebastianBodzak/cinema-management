package pl.com.bottega.cinemamanagement.api;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateShowRequest {

    private Long movieId;
    private Collection<String> dates;
    private CalendarDto calendar;

    public void validate() {
        if ((movieId == null && calendar == null) || (movieId == null))
            throw new InvalidRequestException("Dates or Calendar are required");
        if (dates != null)
            validateDates();
        else
            calendar.validate();
    }

    private void validateDates() {
        //todo
    }

    public Long getMovieId() {
        return movieId;
    }

    public Collection<Date> getParseDates() {
        return null;
    }

    public CalendarDto getCalendar() {
        return calendar;
    }
}
