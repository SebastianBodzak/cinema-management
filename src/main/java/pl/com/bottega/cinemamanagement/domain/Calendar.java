package pl.com.bottega.cinemamanagement.domain;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Bartosz on 2016-09-08.
 */
public class Calendar {

    private Date fromDate;
    private Date untilDate;
    private Collection<WeekDays> weekDays;
    private Collection<Hours> hours;

    public Calendar(Date fromDate, Date untilDate, Collection<WeekDays> weekDays, Collection<Hours> hours) {
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.weekDays = weekDays;
        this.hours = hours;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public Collection<WeekDays> getWeekDays() {
        return weekDays;
    }

    public Collection<Hours> getHours() {
        return hours;
    }
}
