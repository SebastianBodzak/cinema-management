package cinemamanagement.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.AdminPanel;
import pl.com.bottega.cinemamanagement.api.CinemaFactory;
import pl.com.bottega.cinemamanagement.api.CreateCinemaRequest;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.CinemaRepository;
import pl.com.bottega.cinemamanagement.domain.MovieRepository;

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

    @Mock
    private CinemaRepository cinemaRepository;

    @Mock
    private CinemaFactory cinemaFactory;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Cinema cinema;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        adminPanel = new AdminPanel(cinemaRepository, movieRepository, cinemaFactory);
    }

    @Test
    public void shouldCreateCinema() {
        createCinemaRequestInstance(cinemaName, cinemaCity);
        when(cinemaRepository.load(cinemaName, cinemaCity)).thenReturn(null);
        when(cinemaFactory.create(cinemaName, cinemaCity)).thenReturn(cinema);

        adminPanel.createCinema(createCinemaRequest);

        verify(cinemaRepository).save(cinema);
    }

    @Test
    public void shouldNotCreateCinemaBecauseItAlreadyExists() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(cinemaName, cinemaCity);
        when(cinemaRepository.load(cinemaName, cinemaCity)).thenReturn(cinema);

        adminPanel.createCinema(createCinemaRequest);

        exception.expectMessage("Cinema already exists");
    }

    @Test
    public void shouldNotCreateCinemaBecauseNameIsNullValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(null, cinemaCity);

        adminPanel.createCinema(createCinemaRequest);

        exception.expectMessage("value NAME can not be empty");
    }

    @Test
    public void shouldNotCreateCinemaBecauseCityIsNullValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(cinemaName, null);

        adminPanel.createCinema(createCinemaRequest);

        exception.expectMessage("value CITY can not be empty");
    }

    @Test
    public void shouldNotCreateCinemaBecauseCinemaValueIsNull() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequest.setCinema(null);

        adminPanel.createCinema(createCinemaRequest);

        exception.expectMessage("Cinema is required");
    }

    private void createCinemaRequestInstance(String cinemaName, String cinemaCity) {
        CreateCinemaRequest.CinemaDto cinemaDto = createCinemaRequest.new CinemaDto();
        createCinemaRequest.setCinema(cinemaDto);
        cinemaDto.setName(cinemaName);
        cinemaDto.setCity(cinemaCity);
    }
}
