package cinemamanagement.api;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.cinemamanagement.api.CalendarDto;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Bartosz on 2016-09-09.
 */
public class CalendarDTOTest {

    private CalendarDto calendarDto;
    private LocalDateTime fromDate = LocalDateTime.of(2016, 9, 13, 01, 00);
    private LocalDateTime untilDate = LocalDateTime.of(2016, 9, 30, 23, 0);
    private Collection<String> weekDays = new ArrayList<String>() {{
        add("Monday");
        add("Tuesday");
        add("Wednesday");
    }};
    private Collection<String> weekDaysWithNull = new ArrayList<String>() {{
        add(null);
        add("Tuesday");
        add("Wednesday");
    }};
    private Collection<String> weekDaysWithEmptyElement = new ArrayList<String>() {{
        add("Monday");
        add("");
        add("Wednesday");
    }};
    private Collection<String> weekDaysWithNameError = new ArrayList<String>() {{
        add("Mnday");
        add("Tuesday");
        add("Wednesday");
    }};
    private Collection<LocalTime> hours = new ArrayList<LocalTime>() {{
        add(LocalTime.of(13, 0));
        add(LocalTime.of(15, 0));
        add(LocalTime.of(17, 0));
    }};
    private Collection<LocalTime> hoursWithNull = new ArrayList<LocalTime>() {{
        add(null);
        add(LocalTime.of(15, 0));
        add(LocalTime.of(17, 0));
    }};

    @Before
    public void Setup() {
        calendarDto = new CalendarDto();
    }

    @Test
    public void shouldValidate(){
        calendarDto = setCalendarDtoInstance(fromDate, untilDate, weekDays, hours);

        calendarDto.validate();
    }

    @Test
    public void shouldNotValidateWithNullDays(){
        setCalendarDtoInstance(fromDate, untilDate, weekDaysWithNull, hours);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Value of day can not be null", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");

    }

    @Test
    public void shouldNotValidateEmptyDays(){
        setCalendarDtoInstance(fromDate, untilDate, weekDaysWithEmptyElement, hours);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Value weekDays can not be empty", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldNotValidateErrorNameDays(){
        setCalendarDtoInstance(fromDate, untilDate, weekDaysWithNameError, hours);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Value weekDays can not be empty", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldNotValidateWithNullHours(){
        setCalendarDtoInstance(fromDate, untilDate, weekDays, hoursWithNull);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Value of day/hour can not be null", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");

    }

    @Test
    public void shouldNotAllowDateFromToBeGreaterThanUntil(){
            setCalendarDtoInstance(fromDate, LocalDateTime.of(2016, 9, 10, 23, 0), weekDays, hours);

            try {
                calendarDto.validate();
            } catch (InvalidRequestException ex) {
                assertEquals("Date From cant be greater than date until", ex.getMessage());
                return;
            }
            fail("InvalidRequestException expected");
        }

    private CalendarDto setCalendarDtoInstance(LocalDateTime fromDate, LocalDateTime untilDate, Collection<String> weekDays, Collection<LocalTime> hours) {
        calendarDto.setFromDate(fromDate);
        calendarDto.setUntilDate(untilDate);
        calendarDto.setWeekDays(weekDays);
        calendarDto.setHours(hours);
        return calendarDto;
    }
}
