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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Dell on 2016-09-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowsFactoryTest {

    private ShowsFactory showsFactory;

    @Mock
    private Date anyDate;

    @Mock
    private Date anotherDate;

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
        List<Show> shows = showsFactory.createShows(cinema, movie, Arrays.asList(anyDate, anotherDate));

        Show show1 = shows.get(0);
        Show show2 = shows.get(1);
        assertTrue(shows.size() == 2);
        assertEquals(cinema, show1.getCinema());
        assertEquals(movie, show1.getMovie());
        assertEquals(anyDate, show1.getDate());
        assertEquals(cinema, show2.getCinema());
        assertEquals(movie, show2.getMovie());
        assertEquals(anotherDate, show2.getDate());
    }


}
