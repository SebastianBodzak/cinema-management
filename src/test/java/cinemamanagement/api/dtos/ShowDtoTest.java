package cinemamanagement.api.dtos;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.dtos.CalendarDto;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.dtos.ShowDto;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Dell on 2016-09-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowDtoTest {

    private ShowDto showDto;
    private Long movieId = 1L;
    private Collection<LocalDateTime> dates = new LinkedList<>();
    private LocalDateTime date = LocalDateTime.of(2016, 10, 22, 10, 0);
    private LocalDateTime date2 = LocalDateTime.of(2016, 11, 5, 12, 30);

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
        showDto = createShowDtoInstance(movieId, dates, calendarDto);
    }

    @Test
    public void shouldNotVerifyBecauseMovieIdIsNull() {
        exception.expect(InvalidRequestException.class);
        dates.add(date);
        showDto = createShowDtoInstance(null, dates, calendarDto);
        exception.expectMessage("MovieId with dates or movieId with calendar are required");

        showDto.validate();
    }

    @Test
    public void shouldNotVerifyBecauseDatesAndCalendarAreNull() {
        exception.expect(InvalidRequestException.class);
        showDto.setDates(null);
        showDto.setCalendar(null);
        exception.expectMessage("MovieId with dates or movieId with calendar are required");

        showDto.validate();
    }

    @Test
    public void shouldNotVerifyBecauseDatesAndCalendarHaveBeenPutTogether() {
        exception.expect(InvalidRequestException.class);
        dates.add(date);
        showDto = createShowDtoInstance(movieId, dates, calendarDto);
        exception.expectMessage("Can not put dates and calendar at the same time");

        showDto.validate();
    }

    @Test
    public void shouldValidateWithDates() {
        dates.add(date);
        showDto = createShowDtoInstance(movieId, dates, null);

        showDto.validate();
    }

    @Test
    public void shouldValidate() {
        addDatesToList();
        showDto = createShowDto(movieId, dates);

        showDto.validate();
    }

    @Test
    public void shouldNotValidateBecauseOfEmptyDates() {
        exception.expect(InvalidRequestException.class);
        showDto = createShowDto(movieId, dates);
        exception.expectMessage("Dates are required");

        showDto.validate();
    }

    @Test
    public void weekDays() {
        DayOfWeek dayOfWeek = DayOfWeek.valueOf("MONDAY".toUpperCase());
        System.out.println(dayOfWeek);
    }

    private ShowDto createShowDto(Long movieId, Collection<LocalDateTime> dates) {
        ShowDto showDto = new ShowDto();
        showDto.setDates(new HashSet<>(dates));
        showDto.setMovieId(movieId);
        return showDto;
    }

    private void addDatesToList() {
        dates.add(date);
        dates.add(date2);
    }

    private ShowDto createShowDtoInstance(Long movieId, Collection<LocalDateTime> dates, CalendarDto calendarDto) {
        ShowDto showDto = new ShowDto();
        showDto.setMovieId(movieId);
        showDto.setDates(new HashSet<>(dates));
        showDto.setCalendar(calendarDto);
        return showDto;
    }

}
