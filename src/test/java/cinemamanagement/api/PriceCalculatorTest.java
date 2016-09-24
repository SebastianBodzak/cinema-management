package cinemamanagement.api;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.CalculatePriceRequest;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.PriceCalculator;
import pl.com.bottega.cinemamanagement.api.TicketOrderDto;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.ShowsRepository;
import pl.com.bottega.cinemamanagement.domain.TicketPrice;

import java.math.BigDecimal;

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
    private TicketPrice ticketPrice = new TicketPrice("regular", new BigDecimal(15));
    private TicketPrice ticketPrice2 = new TicketPrice("student", new BigDecimal(10));
    private TicketPrice ticketPrice3 = new TicketPrice("regular", new BigDecimal(15));

    @Mock
    private ShowsRepository showsRepository;
    @Mock
    private Show show;


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

    @Test
    public void shouldCalculatePrices() {
        calculatePriceRequest.setShowId(showId);
        calculatePriceRequest.setTickets(Sets.newHashSet(ticketOrderDto, ticketOrderDto2));
        when(showsRepository.findById(showId)).thenReturn(show);
        when(showsRepository.listTicketPrices(calculatePriceRequest.getShowId())).thenReturn(Sets.newHashSet(ticketPrice, ticketPrice2));

        priceCalculator.calculatePrices(calculatePriceRequest);
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
        when(showsRepository.findById(showId)).thenReturn(show);
        when(showsRepository.listTicketPrices(calculatePriceRequest.getShowId())).thenReturn(Sets.newHashSet(ticketPrice, ticketPrice2));

        priceCalculator.calculatePrices(calculatePriceRequest);

    }

}
