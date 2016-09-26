package pl.com.bottega.cinemamanagement.api.requests;

import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.dtos.TicketOrderDto;

import java.util.Set;

/**
 * Created by Dell on 2016-09-19.
 */
public class CalculatePriceRequest {

    private Long showId;
    private Set<TicketOrderDto> tickets;

    public void validate() {
        if (showId == null)
            throw new InvalidRequestException("Missing showId");
        if (tickets == null || tickets.isEmpty())
            throw new InvalidRequestException("No tickets");
        validateTickets();
    }


    private void validateTickets() {
        tickets.forEach(ticket -> ticket.validate());

        for (TicketOrderDto ticket : tickets)
            if (validateIfTicketsHasDuplicates(ticket))
                throw new InvalidRequestException("Duplicate ticket type");
    }

    private boolean validateIfTicketsHasDuplicates(TicketOrderDto ticket) {
        return tickets.stream().filter(e -> e.getKind().equals(ticket.getKind())).count() > 1;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Set<TicketOrderDto> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrderDto> tickets) {
        this.tickets = tickets;
    }
}
