package cinemamanagement.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.CalendarDto;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.ShowPreparationWithCalendar;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Bartosz on 2016-09-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowPreparationWithCalendarTest {

    private CalendarDto calendarDto;
    private ShowPreparationWithCalendar showPreparationWithCalendar;
    private String fromDate = "2016/09/13 01:00";
    private String untilDate = "2016/09/30 23:00";
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
    private Collection<String> hours = new ArrayList<String>() {{
        add("13:00");
        add("15:00");
        add("17:00");
    }};
    private Collection<String> hoursWithNull = new ArrayList<String>() {{
        add(null);
        add("15:00");
        add("17:00");
    }};
    private Collection<String> hoursWithEmptyElement = new ArrayList<String>() {{
        add("13:00");
        add("");
        add("17:00");
    }};
    private Collection<String> hoursWithErrorHour = new ArrayList<String>() {{
        add("13:00");
        add("28:00");
        add("17:00");
    }};

    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;

    @Before
    public void Setup() {
        calendarDto = createCalendarDtoInstance(fromDate, untilDate, weekDays, hours);
        showPreparationWithCalendar = new ShowPreparationWithCalendar();
    }

    @Test
    public void shouldDoNotPrepareShows() {

        showPreparationWithCalendar.prepare(cinema, movie, calendarDto);

    }

    @Test
    public void shouldDoNotPrepareShowsWithWrongDate() {
        calendarDto.setFromDate("2016/09/40 01:00");

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid date", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldDoNotPrepareShowsWithLetterInDate() {
        calendarDto.setFromDate("2016/09/30 p1:00");

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid date", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }
    @Test
    public void shouldDoNotPrepareShowsWithoutProperDate() {
        calendarDto.setFromDate("2016/09/30");

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid date", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldDoNotPrepareShowsWithNullDate() {
        calendarDto.setFromDate(null);

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Date can not be null", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldDoNotPrepareShowsWithEmptyDays() {
        calendarDto.setWeekDays(weekDaysWithEmptyElement);

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid name of day", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldDoNotPrepareShowsWithNullDays() {
        calendarDto.setWeekDays(weekDaysWithNull);

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Day can not be null", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldDoNotPrepareShowsWithErrorNameOfDays() {
        calendarDto.setWeekDays(weekDaysWithNameError);

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid name of day", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldDoNotPrepareShowsWithNullHours() {
        calendarDto.setHours(hoursWithNull);

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Hour can not be null", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldDoNotPrepareShowsWithEmptyHours() {
        calendarDto.setHours(hoursWithEmptyElement);

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid value for hour of day (valid values 0 - 23)", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldDoNotPrepareShowsWithErrorHours() {
        calendarDto.setHours(hoursWithErrorHour);

        try {
            showPreparationWithCalendar.prepare(cinema, movie, calendarDto);
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid value for hour of day (valid values 0 - 23)", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }


    private CalendarDto createCalendarDtoInstance(String fromDate, String untilDate, Collection<String> weekDays, Collection<String> hours) {
        CalendarDto calendarDto = new CalendarDto();
        calendarDto.setFromDate(fromDate);
        calendarDto.setUntilDate(untilDate);
        calendarDto.setWeekDays(weekDays);
        calendarDto.setHours(hours);
        return calendarDto;
    }
}
