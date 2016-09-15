package cinemamanagement.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.ShowsFactory;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Dell on 2016-09-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowsFactoryTest {

    private ShowsFactory showsFactory;
    private List<LocalDateTime> datesList = new LinkedList<>();
    private LocalDateTime anyDate = LocalDateTime.now();
    private LocalDateTime anotherDate = LocalDateTime.now().plusDays(1L);

    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;

    @Before
    public void setUp() {
        showsFactory = new ShowsFactory();
    }

    @Test
    public void shouldCreateShowWithDate() {
        datesList.add(anyDate);
        datesList.add(anotherDate);

        List<Show> shows = showsFactory.createShows(cinema, movie, datesList);

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

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateShowWithDateBecauseCinemaIsNull() {
        showsFactory.createShows(null, movie, datesList);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateShowWithDateBecauseMovieIsNull() {
        showsFactory.createShows(cinema, null, datesList);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateShowWithDateBecauseDatesListIsEmpty() {
        showsFactory.createShows(cinema, movie, datesList);
    }
}
