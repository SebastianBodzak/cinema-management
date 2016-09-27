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
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.repositories.CinemaRepository;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Dell on 2016-09-14.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
@Sql("/fixtures/cinemas.sql")
public class JPACinemaRepositoryTest {

    @Autowired
    private CinemaRepository jpaCinemaRepository;

    private final static Long cinemaId = 10L;

    @Test
    @Transactional
    public void shoulSaveCinema() {
        Cinema cinema = new Cinema("Felicity", "Lublin");

        jpaCinemaRepository.save(cinema);

        Cinema loadedCinema = jpaCinemaRepository.load("Felicity", "Lublin");
        assertEquals("Felicity", loadedCinema.getName());
        assertEquals("Lublin", loadedCinema.getCity());
    }

    @Sql("/fixtures/cinemas.sql")
    @Test
    @Transactional
    public void shouldLoadCinema() {
        Cinema loadedCinema = jpaCinemaRepository.load("Felicity", "Lublin");

        assertEquals("Felicity", loadedCinema.getName());
        assertEquals("Lublin", loadedCinema.getCity());
        assertEquals(cinemaId, loadedCinema.getId());
    }

    @Sql("/fixtures/cinemas.sql")
    @Test
    @Transactional
    public void shouldNotLoadCinema() {
        Cinema loadedCinema = jpaCinemaRepository.load("Felicity", "Timbuktu");

        assertNull(loadedCinema);
    }
}
