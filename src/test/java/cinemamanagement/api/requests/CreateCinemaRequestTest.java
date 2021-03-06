package cinemamanagement.api.requests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.dtos.CinemaDto;
import pl.com.bottega.cinemamanagement.api.requests.CreateCinemaRequest;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.domain.repositories.CinemaRepository;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Dell on 2016-09-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateCinemaRequestTest {
    private CreateCinemaRequest createCinemaRequest;
    private String cinemaName = "anyName";
    private String cinemaCity = "anyCity";
    private String emptyString = "";
    private CinemaDto cinemaDto;

    @Mock
    private CinemaRepository cinemaRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        createCinemaRequest = new CreateCinemaRequest();
    }

    @Test
    public void shouldCorrectlyValidateCinema() {
        createCinemaRequestInstance(cinemaName, cinemaCity);

        createCinemaRequest.validate();

        assertEquals(cinemaName, cinemaDto.getName());
        assertEquals(cinemaCity, cinemaDto.getCity());
    }

    @Test
    public void shouldNotCreateCinemaBecauseNameIsNullValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(null, cinemaCity);
        exception.expectMessage("value NAME can not be empty");

        createCinemaRequest.validate();
    }

    @Test
    public void shouldNotCreateCinemaBecauseNameIsEmptyValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(emptyString, cinemaCity);
        exception.expectMessage("value NAME can not be empty");

        createCinemaRequest.validate();
    }

    @Test
    public void shouldNotCreateCinemaBecauseCityIsNullValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(cinemaName, null);
        exception.expectMessage("value CITY can not be empty");

        createCinemaRequest.validate();
    }

    @Test
    public void shouldNotCreateCinemaBecauseCityIsEmptyValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(cinemaName, emptyString);
        exception.expectMessage("value CITY can not be empty");

        createCinemaRequest.validate();
    }

    @Test
    public void shouldNotCreateCinemaBecauseCinemaValueIsNull() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequest.setCinema(null);
        exception.expectMessage("Cinema is required");

        createCinemaRequest.validate();
    }

    private void createCinemaRequestInstance(String cinemaName, String cinemaCity) {
        cinemaDto = new CinemaDto();
        createCinemaRequest.setCinema(cinemaDto);
        cinemaDto.setName(cinemaName);
        cinemaDto.setCity(cinemaCity);
    }
}
