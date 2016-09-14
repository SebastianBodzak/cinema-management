package pl.com.bottega.cinemamanagement.api.shows.strategies;

import javafx.util.converter.LocalDateTimeStringConverter;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.ShowDto;
import pl.com.bottega.cinemamanagement.api.ShowPreparationStrategy;
import pl.com.bottega.cinemamanagement.domain.*;
import pl.com.bottega.cinemamanagement.domain.Calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Created by Dell on 2016-09-09.
 */
public class ShowPreparationWithCalendar implements ShowPreparationStrategy {

    @Override
    public void validate(ShowDto request) {
        //request.validate();
        request.getCalendar().validate();
    }

    @Override
    public List<Show> prepare(Cinema cinema, Movie movie, ShowDto request) {
        ShowsFactory showsFactory = new ShowsFactory();
        LocalDateTime fromDate = changeStringToDate(request.getCalendar().getFromDate());
        LocalDateTime untilDate = changeStringToDate(request.getCalendar().getUntilDate());
        List<DayOfWeek> weekDays = changeStringDaysToEnumDays(request.getCalendar().getWeekDays());
        List<LocalTime> hoursList = changeStringsToHours(request.getCalendar().getHours());
        Calendar calendar = new Calendar(fromDate,untilDate, weekDays, hoursList);
        return showsFactory.createShows(cinema,movie, calendar);
    }

    private LocalDateTime changeStringToDate(String string){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return LocalDateTime.parse(string,formatter);
    }

    private List<DayOfWeek> changeStringDaysToEnumDays(Collection<String> strings){
        List<DayOfWeek> weekDays = new ArrayList<>();
        DayOfWeek dayOfWeek;
        for (String str : strings) {
            String s = str.toUpperCase();
            dayOfWeek = selectDay(s);
            weekDays.add(dayOfWeek);
        }
        return weekDays;
    }

    private List<LocalTime> changeStringsToHours(Collection<String> strings){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        List<LocalTime> hours = new ArrayList<>();
        for (String str : strings){
            try {
                LocalTime hour = LocalTime.parse(str, formatter);
                hours.add(hour);
            }catch (DateTimeParseException e) {
                throw new InvalidRequestException("Invalid value for hour of day (valid values 0 - 23)");
            }
        }
        return hours;
    }

    private DayOfWeek selectDay(String daySting) {
        if (daySting.equals(DayOfWeek.MONDAY.name()))
            return DayOfWeek.MONDAY;
        else if (daySting.equals(DayOfWeek.TUESDAY.name()))
            return DayOfWeek.TUESDAY;
        else if (daySting.equals(DayOfWeek.WEDNESDAY.name()))
            return DayOfWeek.WEDNESDAY;
        else if (daySting.equals(DayOfWeek.THURSDAY.name()))
            return DayOfWeek.THURSDAY;
        else if (daySting.equals(DayOfWeek.FRIDAY.name()))
            return DayOfWeek.FRIDAY;
        else if (daySting.equals(DayOfWeek.SATURDAY.name()))
            return DayOfWeek.SATURDAY;
        else if (daySting.equals(DayOfWeek.SUNDAY.name()))
            return DayOfWeek.SUNDAY;
        else throw new InvalidRequestException("Wrong name of day");
    }

}
