package cinemamanagement.infrastructure;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.api.CinemaDto;
import pl.com.bottega.cinemamanagement.api.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemamanagement.api.MovieCatalog;
import pl.com.bottega.cinemamanagement.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by arkadiuszarak on 21/09/2016.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
//@Sql("/fixtures/movieshow.sql")
public class JPAMovieCatalogTest {

    @Autowired
    private MovieCatalog jpaMovieCatalog;

    @PersistenceContext
    private EntityManager entityManager;

    private LocalDate testDate = LocalDate.of(2016, 9 ,20);
    private Long testCienmaId = 2L;

    @Sql("/fixtures/movieshow.sql")
    @Test
    @Transactional
    public void shouldlistMoviesInCinema(){
        ListMoviesInCinemaResponse response = jpaMovieCatalog.listMoviesInCinema(testCienmaId, testDate);

        assertTrue(response.getMovies().size() == 1);

        List<ListMoviesInCinemaResponse.MovieDto> movieDtos = Lists.newArrayList(response.getMovies());
        ListMoviesInCinemaResponse.MovieDto dto = movieDtos.get(0);
        List<ListMoviesInCinemaResponse.ShowDto> showDtos = Lists.newArrayList(dto.getShows());
        ListMoviesInCinemaResponse.ShowDto showDto = showDtos.get(0);
        assertEquals(testDate, showDto.getTime());
    }
}
