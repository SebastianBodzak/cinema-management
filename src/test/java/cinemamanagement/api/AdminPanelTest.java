package cinemamanagement.api;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.*;
import pl.com.bottega.cinemamanagement.api.dtos.CinemaDto;
import pl.com.bottega.cinemamanagement.api.dtos.ShowDto;
import pl.com.bottega.cinemamanagement.api.requests.CreateCinemaRequest;
import pl.com.bottega.cinemamanagement.api.requests.CreateMovieRequest;
import pl.com.bottega.cinemamanagement.api.requests.CreateShowRequest;
import pl.com.bottega.cinemamanagement.api.requests.UpdatePriceRequest;
import pl.com.bottega.cinemamanagement.domain.*;
import pl.com.bottega.cinemamanagement.domain.repositories.CinemaRepository;
import pl.com.bottega.cinemamanagement.domain.repositories.MovieRepository;
import pl.com.bottega.cinemamanagement.domain.repositories.ShowsRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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
    private Set<String> movieActors = new HashSet<String>(){{
        add("any actor 1");
        add("any actor 2");
        add("any actor 3");
    }};
    private Set<String> movieGenres = new HashSet<String>(){{
        add("any genres1");
        add("any genres2");
    }};
    private int movieMinAge = 16;
    private int movieLength = 120;
    private CreateMovieRequest.MovieDto movieDto;
    private CreateShowRequest createShowRequest;
    private ShowDto showDto;
    private LocalDateTime date = LocalDateTime.of(2016, 10, 22, 10, 00);
    private LocalDateTime date2 = LocalDateTime.of(2016, 11, 22, 10, 00);
    private Set<LocalDateTime> dates = Sets.newHashSet(date, date2);
    private Long anyMovieId = 1L;
    private Long anyCinemaId = 10L;
    private List<Show> shows = new LinkedList<>();

    @Mock
    private CreateMovieRequest createMovieRequest;

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

    @Mock
    private ShowsFactory showsFactory;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        adminPanel = new AdminPanel(cinemaRepository, movieRepository, cinemaFactory, movieFactory, showsRepository, showsFactory);
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
        exception.expectMessage("Cinema already exists");

        adminPanel.createCinema(createCinemaRequest);
    }

    @Test
    public void shouldCreateMovie(){
        createMovieRequestInstance();
        when(movieFactory.create(createMovieRequest)).thenReturn(movie);

        adminPanel.createMovie(createMovieRequest);

        verify(movieRepository).save(movie);
    }

    @Test
    public void shouldCreateShowsWithDates() {
        createShowsRequestInstance(anyCinemaId, anyMovieId, dates);

        when(cinemaRepository.findById(anyCinemaId)).thenReturn(cinema);
        when(movieRepository.findById(anyMovieId)).thenReturn(movie);

        adminPanel.createShows(createShowRequest);

        verify(showsFactory).createShows(cinema, movie, dates, null);
    }

    @Test
    public void shouldSaveShowsWithDates() {
        createShowsRequestInstance(anyCinemaId, anyMovieId, dates);

        when(cinemaRepository.findById(anyCinemaId)).thenReturn(cinema);
        when(movieRepository.findById(anyMovieId)).thenReturn(movie);
        when(showsFactory.createShows(cinema, movie, dates, null)).thenReturn(new LinkedList<>(Arrays.asList(show, show2)));

        adminPanel.createShows(createShowRequest);

        verify(showsRepository).save(show);
        verify(showsRepository).save(show2);
    }

    @Test
    public void shouldUpdatePrice(){
        when(movieRepository.findById(anyMovieId)).thenReturn(movie);
        UpdatePriceRequest updatePriceRequest = createUpdatePriceRequest();

        adminPanel.updatePrices(updatePriceRequest);
    }

    @Test
    public void shouldNotUpdatePriceBecauseWrongMovieId(){
        exception.expect(InvalidRequestException.class);
        when(movieRepository.findById(anyMovieId)).thenReturn(movie);
        UpdatePriceRequest updatePriceRequest = createUpdatePriceRequest();
        updatePriceRequest.setMovieId(0L);
        exception.expectMessage("Wrong id. Movie does not exist.");

        adminPanel.updatePrices(updatePriceRequest);
    }

    @Test
    public void shouldNotUpdatePriceWithoutStudentTicket(){
        exception.expect(InvalidRequestException.class);
        when(movieRepository.findById(anyMovieId)).thenReturn(movie);
        UpdatePriceRequest updatePriceRequest = createUpdatePriceRequestWithoutStudentTicket();
        exception.expectMessage("Regular and student prices are required");

        adminPanel.updatePrices(updatePriceRequest);
    }

    @Test
    public void shouldNotUpdatePriceWithoutRegularTicket() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        when(movieRepository.findById(anyMovieId)).thenReturn(movie);
        UpdatePriceRequest updatePriceRequest = createUpdatePriceRequestWithoutRegularTicket();
        exception.expectMessage("Regular and student prices are required");

        adminPanel.updatePrices(updatePriceRequest);
    }

    private void createShowsRequestInstance(Long cinemaId, Long movieId, Set<LocalDateTime> dates) {
        createShowRequest = new CreateShowRequest();
        showDto = new ShowDto();
        createShowRequest.setShows(showDto);
        createShowRequest.setCinemaId(cinemaId);
        showDto.setMovieId(movieId);
        showDto.setDates(dates);
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

    private UpdatePriceRequest createUpdatePriceRequest() {
        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();
        updatePriceRequest.setMovieId(anyMovieId);
        HashMap<String,BigDecimal> map = new HashMap<>();
        map.put("regular",new BigDecimal(15.00));
        map.put("student",new BigDecimal(10.00));
        map.put("school",new BigDecimal(8.00));
        map.put("children",new BigDecimal(5.00));
        updatePriceRequest.setPrices(map);
        return updatePriceRequest;
    }
    private UpdatePriceRequest createUpdatePriceRequestWithoutStudentTicket() {
        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();
        updatePriceRequest.setMovieId(anyMovieId);
        HashMap<String,BigDecimal> map = new HashMap<>();
        map.put("regular",new BigDecimal(15.00));
        map.put("school",new BigDecimal(8.00));
        map.put("children",new BigDecimal(5.00));
        updatePriceRequest.setPrices(map);
        return updatePriceRequest;
    }
    private UpdatePriceRequest createUpdatePriceRequestWithoutRegularTicket() {
        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();
        updatePriceRequest.setMovieId(anyMovieId);
        HashMap<String,BigDecimal> map = new HashMap<>();
        map.put("student",new BigDecimal(10.00));
        map.put("school",new BigDecimal(8.00));
        map.put("children",new BigDecimal(5.00));
        updatePriceRequest.setPrices(map);
        return updatePriceRequest;
    }

}
