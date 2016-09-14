package cinemamanagement.api.shows.strategies;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.ShowDto;
import pl.com.bottega.cinemamanagement.api.ShowPreparationStrategy;
import pl.com.bottega.cinemamanagement.api.shows.strategies.ShowPreparationWithDates;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dell on 2016-09-14.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowPreparationWithDatesTest {

    private ShowPreparationStrategy showPreparationStrategy = new ShowPreparationWithDates();
    private ShowDto showDto;
    private Collection<String> dates = new LinkedList<>();
    private Long movieId = 1L;
    private String date = "2016/10/22 10:00";
    private LocalDate expectedDate = LocalDate.of(2016, 10, 22);
    private LocalTime expectedTime = LocalTime.of(10, 00);
    private String date2 = "2016/10/30 13:00";
    private LocalDate expectedDate2 = LocalDate.of(2016, 10, 30);
    private LocalTime expectedTime2 = LocalTime.of(13, 00);

    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldValidate() {
        addDatesToList();
        showDto = createShowDto(movieId, dates);

        showPreparationStrategy.validate(showDto);
    }

    @Test
    public void shouldNotValidateBecauseOfWrongStringFormat() {
        exception.expect(InvalidRequestException.class);
        String date = "2016/13/22 22:00";
        dates.add(date);
        showDto = createShowDto(movieId, dates);

        showPreparationStrategy.validate(showDto);

        exception.expectMessage("Invalid date format");
    }

    @Test
    public void shouldNotValidateBecauseOfEmptyDates() {
        exception.expect(InvalidRequestException.class);
        showDto = createShowDto(movieId, dates);

        showPreparationStrategy.validate(showDto);

        exception.expectMessage("Dates are required");
    }

    @Test
    public void shouldPrepareShows() {
        addDatesToList();
        showDto = createShowDto(movieId, dates);

        List<Show> showList = showPreparationStrategy.prepare(cinema, movie, showDto);

        assertTrue(showList.size() == 2);
        assertEquals(expectedDate, showList.get(0).getDate());
        assertEquals(expectedDate2, showList.get(1).getDate());
        assertEquals(expectedTime, showList.get(0).getTime());
        assertEquals(expectedTime2, showList.get(1).getTime());
        assertEquals(cinema, showList.get(0).getCinema());
        assertEquals(cinema, showList.get(1).getCinema());
        assertEquals(movie, showList.get(0).getMovie());
        assertEquals(movie, showList.get(1).getMovie());
    }

    private ShowDto createShowDto(Long movieId, Collection<String> dates) {
        ShowDto showDto = new ShowDto();
        showDto.setDates(dates);
        showDto.setMovieId(movieId);
        return showDto;
    }

    private void addDatesToList() {
        dates.add(date);
        dates.add(date2);
    }
}
