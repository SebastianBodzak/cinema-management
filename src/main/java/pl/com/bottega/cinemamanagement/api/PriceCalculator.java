package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.*;

import java.util.Set;
import java.util.stream.Collectors;

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
    public CalculatePriceResponse calculatePrices(CalculatePriceRequest request) {
        validateShowId(request.getShowId());
        Show show = showsRepository.findById(request.getShowId());
        Set<TicketPrice> prices = show.getTicketPrices();
        //Set<TicketPrice> prices2 = showsRepository.listTicketPrices(request.getShowId());
        validate(request, prices);
        Set<TicketOrder> ticketOrders = createSetOfTicketOrders(request.getTickets());
        Calculation calculation = new Calculation(ticketOrders, prices);

        return new CalculatePriceResponse(calculation);
    }

    private void validateShowId(Long showId) {
        if (showsRepository.findById(showId) == null)
            throw new InvalidRequestException("Invalid showId");
    }

    private void validate(CalculatePriceRequest request, Set<TicketPrice> prices) {
        request.validate();
        checkIfTicketsAreValid(request.getTickets(), prices);
    }

    private void checkIfTicketsAreValid(Set<TicketOrderDto> tickets, Set<TicketPrice> prices) {
        Set<String> ticketTypes = prices.stream().map(ticket -> ticket.getType()).collect(Collectors.toSet());
        for (TicketOrderDto orderType : tickets)
            if (!ticketTypes.contains(orderType.getKind().toLowerCase()))
                throw new InvalidRequestException("Invalid ticket type");
    }

    private Set<TicketOrder> createSetOfTicketOrders(Set<TicketOrderDto> tickets) {
        return tickets.stream().map(dto -> new TicketOrder(dto.getKind(), dto.getCount())).collect(Collectors.toSet());
    }
}
