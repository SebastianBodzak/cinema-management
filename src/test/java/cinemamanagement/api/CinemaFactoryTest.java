package cinemamanagement.api;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.cinemamanagement.api.CinemaFactory;
import pl.com.bottega.cinemamanagement.domain.Cinema;

import static org.junit.Assert.*;

/**
 * Created by Dell on 2016-09-06.
 */
public class CinemaFactoryTest {

    private CinemaFactory cinemaFactory;
    private String name = "anyName";
    private String city = "anyCity";

    @Before
    public void setUp() {
        cinemaFactory = new CinemaFactory();
    }

    @Test
    public void shouldCreateCinema() {
        Cinema cinema = cinemaFactory.create(name, city);

        assertEquals(name, cinema.getName());
        assertEquals(city, cinema.getCity());
    }
}
