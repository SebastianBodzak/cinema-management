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

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;

    @Before
    public void Setup() {
        calendarDto = new CalendarDto();
        calendarDto.setFromDate(fromDate);
        calendarDto.setUntilDate(untilDate);
        calendarDto.setHours(hours);
        calendarDto.setWeekDays(weekDays);
        showPreparationWithCalendar = new ShowPreparationWithCalendar();
    }

    @Test
    public void shouldDoNotPrepareShows() {
        showPreparationWithCalendar.prepare(cinema, movie, calendarDto);

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
}
