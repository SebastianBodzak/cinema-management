package pl.com.bottega.cinemamanagement.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.dtos.CalendarDto;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Dell on 2016-09-09.
 */
public class ShowDto {

    private Long movieId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Set<LocalDateTime> dates;
    private CalendarDto calendar;

    public void validate() {
        if (checkIfThereAreAllRequiredParameters())
            throw new InvalidRequestException("MovieId with dates or movieId with calendar are required");
        if (checkIfThereAreToManyParameters())
            throw new InvalidRequestException("Can not put dates and calendar at the same time");
        if (!(dates == null)) {
            checkIfDatesAreEmptyCollection();
        } else
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

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public CalendarDto getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarDto calendar) {
        this.calendar = calendar;
    }

    private void checkIfDatesAreEmptyCollection() throws InvalidRequestException {
        if (dates.isEmpty())
            throw new InvalidRequestException("Dates are required");
    }

    public Set<LocalDateTime> getDates() {
        return dates;
    }

    public void setDates(Set<LocalDateTime> dates) {
        this.dates = dates;
    }
}
