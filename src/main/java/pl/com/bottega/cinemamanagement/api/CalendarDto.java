package pl.com.bottega.cinemamanagement.api;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
        if (fromDate == null || untilDate == null)
            throw new InvalidRequestException("Date can not be empty");

        isValidFormat(fromDate);
        isValidFormat(untilDate);

        if (fromDate.compareTo(untilDate) > 0)
            throw new InvalidRequestException("Date From cant be greater than date until");

        if (weekDays == null || checkEmptyElement(weekDays) || weekDays.isEmpty())
            throw new InvalidRequestException("Value weekDays can not be empty");
        if (hours == null || checkEmptyElement(hours) || hours.isEmpty()) {
            throw new InvalidRequestException("Value hours can not be empty");
        }
        isValidHourFormat(hours);
    }


    private void isValidFormat(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        try {
            LocalDateTime.parse(dateString, formatter);
            Utils.checkAdditionalDates(dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidRequestException("Invalid date format");
        }
    }

    private void isValidHourFormat(Collection<String> hours) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            for (String hour : hours)
                LocalTime.parse(hour, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidRequestException("Invalid value of hour");
        }
    }

    private boolean checkEmptyElement(Collection<String> collection) {
        for (String str : collection) {
            if (str == null)
                throw new InvalidRequestException("Value of day/hour can not be null");
            if (str.trim().isEmpty())
                return true;
        }
        return false;
    }

}
