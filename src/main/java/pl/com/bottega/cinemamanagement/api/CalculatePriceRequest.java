package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.TicketOrder;

import java.util.Set;

/**
 * Created by Dell on 2016-09-19.
 */
public class CalculatePriceRequest {

    private Long showId;
    private Set<TicketOrder> tickets;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Set<TicketOrder> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrder> tickets) {
        this.tickets = tickets;
    }
}
