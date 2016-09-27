package cinemamanagement.api;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.PriceCalculator;
import pl.com.bottega.cinemamanagement.api.dtos.TicketOrderDto;
import pl.com.bottega.cinemamanagement.api.requests.CalculatePriceRequest;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.repositories.ShowsRepository;

import static org.mockito.Mockito.when;

/**
 * Created by Dell on 2016-09-23.
 */
@RunWith(MockitoJUnitRunner.class)
public class PriceCalculatorTest {

    private PriceCalculator priceCalculator;
    private CalculatePriceRequest calculatePriceRequest = new CalculatePriceRequest();
    private Long showId = 1l;
    private TicketOrderDto ticketOrderDto = new TicketOrderDto();
    private TicketOrderDto ticketOrderDto2 = new TicketOrderDto();
    private TicketOrderDto ticketOrderDto3 = new TicketOrderDto();

    @Mock
    private ShowsRepository showsRepository;
    @Mock
    private Show show;
    @Mock
    private Movie movie;


    @Before
    public void setUp() {
        priceCalculator = new PriceCalculator(showsRepository);
        ticketOrderDto.setKind("regular");
        ticketOrderDto.setCount(2);
        ticketOrderDto2.setKind("student");
        ticketOrderDto2.setCount(2);
        ticketOrderDto3.setKind("regular");
        ticketOrderDto3.setCount(2);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowErrorBecauseOfInvalidShowId() {
        calculatePriceRequest.setShowId(showId);
        calculatePriceRequest.setTickets(Sets.newHashSet(ticketOrderDto, ticketOrderDto2));
        when(showsRepository.findById(showId)).thenReturn(null);

        priceCalculator.calculatePrices(calculatePriceRequest);
    }


    @Test(expected = InvalidRequestException.class)
    public void shouldThrowErrorBecauseOfInvalidTicketType() {
        ticketOrderDto.setKind("invalid type");
        calculatePriceRequest.setShowId(showId);
        calculatePriceRequest.setTickets(Sets.newHashSet(ticketOrderDto, ticketOrderDto2));
        when(showsRepository.findShowWithTicketPrices(showId)).thenReturn(show);

        priceCalculator.calculatePrices(calculatePriceRequest);

    }

}
