package cinemamanagement.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.CalendarDto;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.ShowDto;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;
import java.util.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
/**
 * Created by Dell on 2016-09-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowDtoTest {

    private ShowDto showDto;
    private Long movieId = 1L;
    private Collection<String> dates = new LinkedList<>();
    private String date = "2016/10/22 10:00";
    private String date2 = "2016/11/05 12:30";
    private List<Show> showList;

    @Mock
    private CalendarDto calendarDto;

    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        showDto = new ShowDto();
    }

    @Test
    public void shouldNotVerifyBecauseMovieIdIsNull() {
        exception.expect(InvalidRequestException.class);
        dates.add("2016/10/22 10:00");
        showDto = createShowDtoInstance(null, dates, calendarDto);

        showDto.validate();

        exception.expectMessage("movieId with dates or movieId with calendar are required");
    }

    @Test
    public void shouldNotVerifyBecauseDatesAndCalendarAreNull() {
        exception.expect(InvalidRequestException.class);
        showDto = createShowDtoInstance(movieId, null, null);

        showDto.validate();

        exception.expectMessage("movieId with dates or movieId with calendar are required");
    }

    @Test
    public void shouldNotVerifyBecauseAllArgumentsAreNull() {
        exception.expect(InvalidRequestException.class);
        showDto = createShowDtoInstance(null, null, null);

        showDto.validate();

        exception.expectMessage("movieId with dates or movieId with calendar are required");
    }

    @Test
    public void shouldNotVerifyBecauseDatesAndCalendarHaveBeenPutTogether() {
        exception.expect(InvalidRequestException.class);
        dates.add(date);
        showDto = createShowDtoInstance(movieId, dates, calendarDto);

        showDto.validate();

        exception.expectMessage("can not put dates and calendar at the same time");
    }

    @Test
    public void shouldValidateWithDates() {
        dates.add(date);
        showDto = createShowDtoInstance(movieId, dates, null);

        showDto.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotPrepareBecauseCinemaIsNull() {
        showDto = new ShowDto();

        showDto.prepareShow(null, movie);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotPrepareBecauseMovieIsNull() {
        showDto = new ShowDto();

        showDto.prepareShow(cinema, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotPrepareBecauseBothArgumentsAreNull() {
        showDto = new ShowDto();

        showDto.prepareShow(null, null);
    }

    @Test
    public void shouldPrepareShowsWithDates() {
        dates.add(date);
        dates.add(date2);
        showDto = createShowDtoInstance(movieId, dates, null);

        showList = showDto.prepareShow(cinema, movie);

        assertTrue(showList.size() == 2);
        assertEquals(cinema, showList.get(0).getCinema());
        assertEquals(cinema, showList.get(1).getCinema());
        assertEquals(movie, showList.get(0).getMovie());
        assertEquals(movie, showList.get(1).getMovie());
    }

    private ShowDto createShowDtoInstance(Long movieId, Collection<String> dates, CalendarDto calendarDto) {
        ShowDto showDto = new ShowDto();
        showDto.setMovieId(movieId);
        showDto.setDates(dates);
        showDto.setCalendar(calendarDto);
        return showDto;
    }

}
