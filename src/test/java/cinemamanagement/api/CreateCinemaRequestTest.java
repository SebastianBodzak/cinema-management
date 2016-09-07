package cinemamanagement.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.CinemaDto;
import pl.com.bottega.cinemamanagement.api.CreateCinemaRequest;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.domain.CinemaRepository;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Dell on 2016-09-06.
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

        createCinemaRequest.validate(cinemaRepository);

        assertEquals(cinemaName, cinemaDto.getName());
        assertEquals(cinemaCity, cinemaDto.getCity());
    }

    @Test
    public void shouldNotCreateCinemaBecauseNameIsNullValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(null, cinemaCity);

        createCinemaRequest.validate(cinemaRepository);

        exception.expectMessage("value NAME can not be empty");
    }

    @Test
    public void shouldNotCreateCinemaBecauseNameIsEmptyValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(emptyString, cinemaCity);

        createCinemaRequest.validate(cinemaRepository);

        exception.expectMessage("value NAME can not be empty");
    }

    @Test
    public void shouldNotCreateCinemaBecauseCityIsNullValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(cinemaName, null);

        createCinemaRequest.validate(cinemaRepository);

        exception.expectMessage("value CITY can not be empty");
    }

    @Test
    public void shouldNotCreateCinemaBecauseCityIsEmptyValue() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequestInstance(cinemaName, emptyString);

        createCinemaRequest.validate(cinemaRepository);

        exception.expectMessage("value CITY can not be empty");
    }

    @Test
    public void shouldNotCreateCinemaBecauseCinemaValueIsNull() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        createCinemaRequest.setCinema(null);

        createCinemaRequest.validate(cinemaRepository);

        exception.expectMessage("Cinema is required");
    }

    private void createCinemaRequestInstance(String cinemaName, String cinemaCity) {
        cinemaDto = new CinemaDto();
        createCinemaRequest.setCinema(cinemaDto);
        cinemaDto.setName(cinemaName);
        cinemaDto.setCity(cinemaCity);
    }

}

