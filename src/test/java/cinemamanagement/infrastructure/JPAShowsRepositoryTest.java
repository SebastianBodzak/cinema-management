package cinemamanagement.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.infrastructure.JPAShowsRepository;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dell on 2016-09-23.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
@Sql("/fixtures/shows.sql")
public class JPAShowsRepositoryTest {

    @Autowired
    private JPAShowsRepository showsRepository;

    private Long showId = 12L;

    @Sql("/fixtures/shows.sql")
    @Test
    @Transactional
    public void shouldlistMoviesInCinema() {
        Show show = showsRepository.showWithTicketPrices(showId);

        assertTrue(show.getTicketPrices().size() == 2);
    }

    @Sql("/fixtures/shows.sql")
    @Test
    @Transactional
    public void shouldFindShowsWithReservationsAndPrices() {
        Show show = showsRepository.findShowWithReservations(showId);

        assertTrue(show.getReservations().size() == 2);
        assertTrue(show.getTicketPrices().size() == 2);
        assertEquals("any title", show.getMovie().getTitle());
    }
}
