package pl.com.bottega.cinemamanagement.api;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Dell on 2016-09-04.
 */
public class CalendarDto {

    private Date fromDate;
    private Date untilDate;
    private Collection<String> weekDays;
    private Collection<String> hours;

    public void validate() {
        //todo
    }
}
