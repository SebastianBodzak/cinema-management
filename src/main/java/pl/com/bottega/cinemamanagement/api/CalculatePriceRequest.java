package pl.com.bottega.cinemamanagement.api;

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
//        for (TicketOrderDto ticket : tickets) //todo case that will not occur???
//            if (Collections.frequency(tickets, ticket) > 1)
//                throw new InvalidRequestException("Duplicate ticket type");
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
