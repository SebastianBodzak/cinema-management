package pl.com.bottega.cinemamanagement.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * Created by Dell on 2016-09-04.
 */
public class CalendarDto {

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime fromDate;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime untilDate;
    private Collection<String> weekDays;
    @JsonFormat(pattern = "HH:mm")
    private Collection<LocalTime> hours;

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDateTime untilDate) {
        this.untilDate = untilDate;
    }

    public Collection<String> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Collection<String> weekDays) {
        this.weekDays = weekDays;
    }

    public Collection<LocalTime> getHours() {
        return hours;
    }

    public void setHours(Collection<LocalTime> hours) {
        this.hours = hours;
    }

    public void validate() {
        if (fromDate == null || untilDate == null)
            throw new InvalidRequestException("Date can not be empty");

        if (fromDate.compareTo(untilDate) > 0)
            throw new InvalidRequestException("Date From cant be greater than date until");

        if (weekDays == null || checkEmptyWeekElement(weekDays) || weekDays.isEmpty())
            throw new InvalidRequestException("Value weekDays can not be empty");
        if (hours == null || hours.isEmpty()) {
            throw new InvalidRequestException("Value hours can not be empty");
        }
    }

    private boolean checkEmptyWeekElement(Collection<String> collection) {
        for (String str : collection) {
            if (str == null)
                throw new InvalidRequestException("Value of day can not be null");
            if (str.trim().isEmpty())
                return true;
        }
        return false;
    }

}
