package cinemamanagement.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.*;
import pl.com.bottega.cinemamanagement.domain.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    private CinemaDto cinemaDto;

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
    private CreateShowRequest createShowRequest;
    private ShowDto showDto;
    private Collection<String> stringDates;
    private String stringDate = "2016/10/22 10:00";
    private String stringDate2 = "2016/11/22 10:00";
    private Long anyMovieId = 1L;
    private Long anyCinemaId = 10L;
    private List<Show> shows = new LinkedList<>();

    @Mock
    private CinemaRepository cinemaRepository;

    @Mock
    private CinemaFactory cinemaFactory;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieFactory movieFactory;

    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;

    @Mock
    private Show show;

    @Mock
    private Show show2;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        adminPanel = new AdminPanel(cinemaRepository, movieRepository, cinemaFactory, movieFactory, showsRepository);
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

    @Test
    public void shouldCreateShowsWithDates() throws ParseException {
        createShowsRequestInstance();
        stringDates.add(stringDate);
        stringDates.add(stringDate2);
        when(cinemaRepository.findById(anyCinemaId)).thenReturn(cinema);
        when(movieRepository.findById(anyMovieId)).thenReturn(movie);

        adminPanel.createShows(anyCinemaId, createShowRequest);

//        verify(showsRepository, times(2)).save(show);
    }

//    @Test
//    public void shouldPrepareShowsWithDates() {
//        dates.add(date);
//        dates.add(date2);
//        showDto = createShowDtoInstance(movieId, dates, null);
//
//        showList = showDto.prepareShow(cinema, movie);
//
//        assertTrue(showList.size() == 2);
//        assertEquals(cinema, showList.get(0).getCinema());
//        assertEquals(cinema, showList.get(1).getCinema());
//        assertEquals(movie, showList.get(0).getMovie());
//        assertEquals(movie, showList.get(1).getMovie());
//    }

//    @Test
//    public void shouldPrepareShows() {
//        addDatesToList();
//        showDto = createShowDto(movieId, dates);
//
//        List<Show> showList = showPreparationStrategy.prepare(cinema, movie, showDto);
//
//        assertTrue(showList.size() == 2);
//        assertEquals(expectedDate, showList.get(0).getDate());
//        assertEquals(expectedDate2, showList.get(1).getDate());
//        assertEquals(expectedTime, showList.get(0).getTime());
//        assertEquals(expectedTime2, showList.get(1).getTime());
//        assertEquals(cinema, showList.get(0).getCinema());
//        assertEquals(cinema, showList.get(1).getCinema());
//        assertEquals(movie, showList.get(0).getMovie());
//        assertEquals(movie, showList.get(1).getMovie());
//    }

    private void createShowsRequestInstance() {
        createShowRequest = new CreateShowRequest();
        showDto = new ShowDto();
        createShowRequest.setShows(showDto);
        showDto.setMovieId(anyMovieId);
        stringDates = new LinkedList<>();
        showDto.setDates(stringDates);
    }

    private void createCinemaRequestInstance() {
        cinemaDto = new CinemaDto();
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
