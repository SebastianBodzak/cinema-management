package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.*;
import pl.com.bottega.cinemamanagement.domain.Calendar;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Created by Dell on 2016-09-09.
 */
public class ShowPreparationWithCalendar {

    public List<Show> prepare(Cinema cinema, Movie movie, CalendarDto calendarDto) {
        ShowsFactory showsFactory = new ShowsFactory();
        LocalDateTime fromDate = changeStringToDate(calendarDto.getFromDate());
        LocalDateTime untilDate = changeStringToDate(calendarDto.getUntilDate());
        List<DayOfWeek> weekDays = changeStringDaysToEnumDays(calendarDto.getWeekDays());
        List<LocalTime> hoursList = changeStringsToHours(calendarDto.getHours());
        Calendar calendar = new Calendar(fromDate, untilDate, weekDays, hoursList);
        return showsFactory.createShows(cinema, movie, calendar);
    }

    private LocalDateTime changeStringToDate(String string) {
        if (string == null)
            throw new InvalidRequestException("Date can not be null");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        try {
            return LocalDateTime.parse(string, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidRequestException("Invalid date");
        }
    }

    private List<DayOfWeek> changeStringDaysToEnumDays(Collection<String> strings) {
        List<DayOfWeek> weekDays = new ArrayList<>();
        for (String str : strings) {
            if (str == null)
                throw new InvalidRequestException("Day can not be null");
            try {
                weekDays.add(DayOfWeek.valueOf(str.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new InvalidRequestException("Invalid name of day");
            }
        }
        return weekDays;
    }

    private List<LocalTime> changeStringsToHours(Collection<String> strings) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        List<LocalTime> hours = new ArrayList<>();
        for (String str : strings) {
            if (str == null)
                throw new InvalidRequestException("Hour can not be null");
            try {
                LocalTime hour = LocalTime.parse(str, formatter);
                hours.add(hour);
            } catch (DateTimeParseException e) {
                throw new InvalidRequestException("Invalid value for hour of day (valid values 0 - 23)");
            }
        }
        return hours;
    }


}
