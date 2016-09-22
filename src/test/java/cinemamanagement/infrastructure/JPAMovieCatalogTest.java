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
import pl.com.bottega.cinemamanagement.api.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemamanagement.api.MovieCatalog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

/**
 * Created by arkadiuszarak on 21/09/2016.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
@Sql("/fixtures/MovieShow.sql")
public class JPAMovieCatalogTest {

    @Autowired
    private MovieCatalog jpaMovieCatalog;

    @PersistenceContext
    private EntityManager entityManager;

    private LocalDate testDate = LocalDate.of(2016, 9 ,21);
    private Long testCienmaId = 12L;

    @Test
    @Transactional
    public void shouldlistMoviesInCinema(){

        ListMoviesInCinemaResponse response = jpaMovieCatalog.listMoviesInCinema(testCienmaId, testDate);
    }

}
