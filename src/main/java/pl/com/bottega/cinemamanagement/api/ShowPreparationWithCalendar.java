package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dell on 2016-09-09.
 */
public class ShowPreparationWithCalendar {

    public List<Show> prepare(Cinema cinema, Movie movie, CalendarDto calendarDto) {
        if (cinema == null || movie == null || calendarDto == null)
            throw new InvalidRequestException("Date can not be null");

        ShowsFactory showsFactory = new ShowsFactory();
        List<DayOfWeek> weekDays = changeStringDaysToEnumDays(calendarDto.getWeekDays());
        Calendar calendar = new Calendar(calendarDto.getFromDate(), calendarDto.getUntilDate(), weekDays, new LinkedList<>(calendarDto.getHours()));
        return showsFactory.createShows(cinema, movie, calendar);
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
