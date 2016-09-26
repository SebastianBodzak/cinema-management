package cinemamanagement.infrastructure;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.api.responses.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemamanagement.api.MovieCatalog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by arkadiuszarak on 21/09/2016.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
public class JPAMovieCatalogTest {

    @Autowired
    private MovieCatalog jpaMovieCatalog;

    @PersistenceContext
    private EntityManager entityManager;

    private LocalDate testDate = LocalDate.of(2016, 9 ,20);
    private Long testCienmaId = 2L;
    private String anyTitle = "any title";
    private String anyDescription = "any description";
    private Long showId = 12L;
    private Integer minAge = 18;
    private Integer length = 120;
    private Set<String> actors = new HashSet<String>(){{add("Himilsbach"); }};
    private LocalDateTime anyDate = LocalDateTime.of(2016, 9 ,20, 13, 05, 00);

    @Sql("/fixtures/movieshow.sql")
    @Test
    @Transactional
    public void shouldlistMoviesInCinema(){

        ListMoviesInCinemaResponse response = jpaMovieCatalog.listMoviesInCinema(testCienmaId, testDate);

        List<ListMoviesInCinemaResponse.MovieDto> movieDtos = Lists.newArrayList(response.getMovies());
        ListMoviesInCinemaResponse.MovieDto dto = movieDtos.get(0);

        List<ListMoviesInCinemaResponse.ShowDto> showDtos = Lists.newArrayList(dto.getShows());
        ListMoviesInCinemaResponse.ShowDto showDto = showDtos.get(0);

        assertTrue(response.getMovies().size() == 1);
        assertEquals(anyTitle, dto.getTitle());
        assertEquals(anyDescription, dto.getDescription());
        assertEquals(minAge,dto.getMinAge());
        assertEquals(length, dto.getLength());
        assertEquals(actors, dto.getActors());
        assertEquals(showId, showDto.getId());
        assertEquals(anyDate.toLocalTime(), showDto.getTime());
    }
}
