package pl.com.bottega.cinemamanagement.api;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateShowRequest {

    private Long movieId;
    private Collection<Date> dates;
    private CalendarDto calendar;

    public void validate() {
        if (movieId == null) {
            throw new InvalidRequestException("value movieID can not be empty");
        }

        if (dates == null && calendar == null) {
            throw new InvalidRequestException("value date can not be empty");
        }


    }
}
