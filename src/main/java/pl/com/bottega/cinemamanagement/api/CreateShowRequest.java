package pl.com.bottega.cinemamanagement.api;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateShowRequest {

    private ShowDto shows;
    private Long cinemaId;

    public void validate() {
        if (shows == null)
            throw new InvalidRequestException("Shows are required");
        shows.validate();
    }

    public CalendarDto getCalendarDto() {
        return shows.getCalendar();
    }

    public Set<LocalDateTime> getDates() {
        return shows.getDates();
    }

    public ShowDto getShows() {
        return shows;
    }

    public void setShows(ShowDto shows) {
        this.shows = shows;
    }

    public Long getMovieId() {
        return shows.getMovieId();
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }
}
