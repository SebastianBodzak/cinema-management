package pl.com.bottega.cinemamanagement.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bartosz on 2016-09-08.
 */
public class Calendar {

    private LocalDate fromDate;
    private LocalDate untilDate;
    private List<DayOfWeek> weekDays = new LinkedList<>();
    private List<LocalTime> hours = new LinkedList<>();

    public Calendar(LocalDate fromDate, LocalDate untilDate, List<DayOfWeek> weekDays, List<LocalTime> hours) {
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.weekDays = weekDays;
        this.hours = hours;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getUntilDate() {
        return untilDate;
    }

    public Collection<DayOfWeek> getWeekDays() {
        return weekDays;
    }

    public Collection<LocalTime> getHours() {
        return hours;
    }
}
