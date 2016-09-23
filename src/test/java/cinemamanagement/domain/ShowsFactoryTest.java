package cinemamanagement.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.domain.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Dell on 2016-09-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowsFactoryTest {

    private ShowsFactory showsFactory;
    private Set<LocalDateTime> datesList = new HashSet<>();
    private LocalDateTime anyDate = LocalDateTime.of(2016, 9, 10, 21, 00);
    private LocalDateTime anotherDate = LocalDateTime.of(2016, 9, 11, 22, 00);
    private LocalDateTime fromDate = LocalDateTime.of(2016, 9, 19, 12, 00);
    private LocalDateTime untilDate = LocalDateTime.of(2016, 9, 23, 12, 00);
    private List<DayOfWeek> weekDays;
    private List<LocalTime> hours;


    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;
    @Mock
    private Calendar calendar;

    @Before
    public void setUp() {
        showsFactory = new ShowsFactory();
    }

    @Test
    public void shouldCreateShowWithDate() {
        datesList.add(anyDate);
        datesList.add(anotherDate);

        List<Show> shows = showsFactory.createShows(cinema, movie, datesList, null);

        Show show1 = shows.get(0);
        Show show2 = shows.get(1);
        assertTrue(shows.size() == 2);
        assertEquals(cinema, show1.getCinema());
        assertEquals(movie, show1.getMovie());
        assertEquals(anyDate.toLocalDate(), show1.getDate());
        assertEquals(anyDate.toLocalTime(), show1.getTime());
        assertEquals(cinema, show2.getCinema());
        assertEquals(movie, show2.getMovie());
        assertEquals(anotherDate.toLocalDate(), show2.getDate());
        assertEquals(anotherDate.toLocalTime(), show2.getTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateShowWithDateBecauseCinemaIsNull() {
        showsFactory.createShows(null, movie, datesList, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateShowWithDateBecauseMovieIsNull() {
        showsFactory.createShows(cinema, null, datesList, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateShowWithDateBecauseDatesListIsEmpty() {
        showsFactory.createShows(cinema, movie, datesList, null);
    }

    @Test
    public void shouldCreateShowWithCalendar() {
        calendar = createCalendar();

        List<Show> shows = showsFactory.createShows(cinema, movie, null, calendar);

        Show show1 = shows.get(0);
        Show show2 = shows.get(1);
        Show show3 = shows.get(2);
        Show show4 = shows.get(3);

        assertTrue(shows.size() == 4);

        assertEquals(cinema, show1.getCinema());
        assertEquals(movie, show1.getMovie());
        assertEquals(LocalDate.of(2016,9,20), show1.getDate());
        assertEquals(LocalTime.of(13,0), show1.getTime());

        assertEquals(cinema, show2.getCinema());
        assertEquals(movie, show2.getMovie());
        assertEquals(LocalDate.of(2016,9,20), show2.getDate());
        assertEquals(LocalTime.of(16,0), show2.getTime());

        assertEquals(cinema, show3.getCinema());
        assertEquals(movie, show3.getMovie());
        assertEquals(LocalDate.of(2016,9,21), show3.getDate());
        assertEquals(LocalTime.of(13,0), show3.getTime());

        assertEquals(cinema, show4.getCinema());
        assertEquals(movie, show4.getMovie());
        assertEquals(LocalDate.of(2016,9,21), show4.getDate());
        assertEquals(LocalTime.of(16,0), show4.getTime());
    }

    private Calendar createCalendar() {
        weekDays = new LinkedList<DayOfWeek>() {{
            add(WEDNESDAY);
            add(TUESDAY);
        }};
        hours = new LinkedList<LocalTime>() {{
            add(LocalTime.of(13,0));
            add(LocalTime.of(16,0));
        }};
        Calendar calendar = new Calendar(fromDate, untilDate, weekDays, hours);
        return calendar;
    }


}
