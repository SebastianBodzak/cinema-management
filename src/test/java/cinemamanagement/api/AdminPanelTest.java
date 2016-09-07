package cinemamanagement.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.*;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.CinemaRepository;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.MovieRepository;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;

/**
 * Created by Dell on 2016-09-06.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminPanelTest {

    private AdminPanel adminPanel;
    private String cinemaName = "anyName";
    private String cinemaCity = "anyCity";
    private CreateCinemaRequest createCinemaRequest = new CreateCinemaRequest();
    private CreateCinemaRequest.CinemaDto cinemaDto;

    private String movieTitle = "any title";
    private String movieDescription = "any description";
    private Collection<String> movieActors = new ArrayList<String>(){{
        add("any actor 1");
        add("any actor 2");
        add("any actor 3");
    }};
    private Collection<String> movieGenres = new ArrayList<String>(){{
        add("any genres1");
        add("any genres2");
    }};
    private int movieMinAge = 16;
    private int movieLength = 120;
    private CreateMovieRequest createMovieRequest = new CreateMovieRequest();
    private CreateMovieRequest.MovieDto movieDto;

    @Mock
    private CinemaRepository cinemaRepository;

    @Mock
    private CinemaFactory cinemaFactory;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieFactory movieFactory;

    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        adminPanel = new AdminPanel(cinemaRepository, movieRepository, cinemaFactory, movieFactory);
    }

    @Test
    public void shouldCreateCinema() {
        createCinemaRequestInstance();
        when(cinemaRepository.load(cinemaName, cinemaCity)).thenReturn(null);
        when(cinemaFactory.create(cinemaName, cinemaCity)).thenReturn(cinema);

        adminPanel.createCinema(createCinemaRequest);

        verify(cinemaRepository).save(cinema);
    }

    @Test
    public void shouldNotCreateCinemaBecauseItAlreadyExists() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance();
        when(cinemaRepository.load(cinemaName, cinemaCity)).thenReturn(cinema);

        adminPanel.createCinema(createCinemaRequest);

        exception.expectMessage("Cinema already exists");
    }

    @Test
    public void shouldCreateMovie(){
        createMovieRequestInstance();
        when(movieFactory.create(movieTitle, movieDescription, movieActors, movieGenres, movieMinAge, movieLength)).thenReturn(movie);

        adminPanel.createMovie(createMovieRequest);

        verify(movieRepository).save(movie);
    }

    private void createCinemaRequestInstance() {
        cinemaDto = createCinemaRequest.new CinemaDto();
        createCinemaRequest.setCinema(cinemaDto);
        cinemaDto.setName(cinemaName);
        cinemaDto.setCity(cinemaCity);
    }

    private void createMovieRequestInstance(){
        movieDto = createMovieRequest.new MovieDto();
        createMovieRequest.setMovie(movieDto);
        movieDto.setTitle(movieTitle);
        movieDto.setDescription(movieDescription);
        movieDto.setActors(movieActors);
        movieDto.setGeners(movieGenres);
        movieDto.setMinAge(movieMinAge);
        movieDto.setLenght(movieLength);
    }
}
