package cinemamanagement.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.CreateCinemaRequest;
import pl.com.bottega.cinemamanagement.domain.CinemaRepository;

import static org.mockito.Mockito.when;

/**
 * Created by Dell on 2016-09-06.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateCinemaRequestTest {

    private CreateCinemaRequest createCinemaRequest;

    @Mock
    private CinemaRepository cinemaRepository;

    @Test
    public void shouldValidateCinema() {
    }
}

