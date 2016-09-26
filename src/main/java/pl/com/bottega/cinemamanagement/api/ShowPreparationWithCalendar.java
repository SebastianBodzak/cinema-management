package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.api.dtos.CalendarDto;
import pl.com.bottega.cinemamanagement.domain.Calendar;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dell on 2016-09-09.
 */
public class ShowPreparationWithCalendar {

    public Calendar prepare(Cinema cinema, Movie movie, CalendarDto calendarDto) {
        if (cinema == null || movie == null || calendarDto == null)
            throw new InvalidRequestException("Date can not be null");

        List<DayOfWeek> weekDays = changeStringDaysToEnumDays(calendarDto.getWeekDays());
        return new Calendar(calendarDto.getFromDate(), calendarDto.getUntilDate(), weekDays, new LinkedList<>(calendarDto.getHours()));
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
}
