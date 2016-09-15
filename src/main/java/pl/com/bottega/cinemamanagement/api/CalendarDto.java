package pl.com.bottega.cinemamanagement.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;

/**
 * Created by Dell on 2016-09-04.
 */
public class CalendarDto {

    private String fromDate;
    private String untilDate;
    private Collection<String> weekDays;
    private Collection<String> hours;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        isValidFormat(fromDate);
        this.fromDate = fromDate;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = untilDate;
    }

    public Collection<String> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Collection<String> weekDays) {
        this.weekDays = weekDays;
    }

    public Collection<String> getHours() {
        return hours;
    }

    public void setHours(Collection<String> hours) {
        this.hours = hours;
    }

    public void validate() {
        isValidFormat(fromDate);
        isValidFormat(untilDate);

        if (weekDays == null || checkEmptyElement(weekDays))
            throw new InvalidRequestException("value weekDays can not be empty");

        if (hours == null || checkEmptyElement(hours))
            throw new InvalidRequestException("value hours can not be empty");
    }


    private void isValidFormat(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        try {
            LocalDateTime.parse(dateString,formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidRequestException("Invalid date format");
        }
    }

    private boolean checkEmptyElement(Collection<String> collection) {
        for (String str : collection) {
            if (str.trim().isEmpty())
                return true;
        }
        return false;
    }

}
