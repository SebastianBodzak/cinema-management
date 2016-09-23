package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.Calculation;
import pl.com.bottega.cinemamanagement.domain.ShowsRepository;
import pl.com.bottega.cinemamanagement.domain.TicketOrder;
import pl.com.bottega.cinemamanagement.domain.TicketPrice;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Dell on 2016-09-19.
 */
@Service
public class PriceCalculator {

    private ShowsRepository showsRepository;

    public PriceCalculator(ShowsRepository showsRepository) {
        this.showsRepository = showsRepository;
    }

    @Transactional
    public CalculatePriceResponse calculatePrice(CalculatePriceRequest request) {
        Set<TicketPrice> prices = showsRepository.listTicketPrices(request.getShowId());
        //validate ticketOrder todo
        Set<TicketOrder> ticketOrders = createSetOfTicketOrders(request.getTickets());
        Calculation calculation = new Calculation(ticketOrders, prices);
        return new CalculatePriceResponse(calculation);
    }

    private Set<TicketOrder> createSetOfTicketOrders(Set<TicketOrderDto> tickets) {
        Set<TicketOrder> ticketOrders = new HashSet<>();
        for (TicketOrderDto dto : tickets)
            ticketOrders.add(new TicketOrder(dto.getKind(), dto.getCount()));
        return ticketOrders;
    }
}
