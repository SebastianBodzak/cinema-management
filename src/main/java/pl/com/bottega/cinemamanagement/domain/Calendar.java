package pl.com.bottega.cinemamanagement.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bartosz on 2016-09-08.
 */
public class Calendar {

    private LocalDateTime fromDate;
    private LocalDateTime untilDate;
    private List<DayOfWeek> weekDays = new LinkedList<>();
    private List<LocalTime> hours = new LinkedList<>();

    public Calendar(LocalDateTime fromDate, LocalDateTime untilDate, List<DayOfWeek> weekDays, List<LocalTime> hours) {
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.weekDays = weekDays;
        this.hours = hours;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getUntilDate() {
        return untilDate;
    }

    public Collection<DayOfWeek> getWeekDays() {
        return weekDays;
    }

    public Collection<LocalTime> getHours() {
        return hours;
    }

}
