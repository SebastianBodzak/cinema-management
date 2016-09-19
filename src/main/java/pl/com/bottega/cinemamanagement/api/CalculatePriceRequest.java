package pl.com.bottega.cinemamanagement.api;

import java.util.Set;

/**
 * Created by Dell on 2016-09-19.
 */
public class CalculatePriceRequest {

    private Long showId;
    private Set<TicketOrderDto> tickets;

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
