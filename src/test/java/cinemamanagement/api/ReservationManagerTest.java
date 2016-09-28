package cinemamanagement.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.PriceCalculator;
import pl.com.bottega.cinemamanagement.api.ReservationManager;
import pl.com.bottega.cinemamanagement.api.requests.CreateReservationRequest;
import pl.com.bottega.cinemamanagement.domain.repositories.ReservationRepository;
import pl.com.bottega.cinemamanagement.domain.repositories.ShowsRepository;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by Dell on 2016-09-28.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationManagerTest {

    private ReservationManager reservationManager;
    private Long showId = 9999999999L;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private PriceCalculator priceCalculator;

    @Mock
    private CreateReservationRequest createReservationRequest;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        reservationManager = new ReservationManager(reservationRepository, showsRepository, priceCalculator);
    }

    @Test
    public void shouldThrowExceptionBecauseOfInvalidShowId() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        doNothing().when(createReservationRequest).validate();
        when(showsRepository.findShowWithReservations(showId)).thenReturn(null);
        exception.expectMessage("Wrong show id");

        reservationManager.createReservation(createReservationRequest);
    }
}
