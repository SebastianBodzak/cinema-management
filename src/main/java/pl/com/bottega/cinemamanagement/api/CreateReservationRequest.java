package pl.com.bottega.cinemamanagement.api;

import java.util.Set;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class CreateReservationRequest {

    private Long showId;
    private TicketOrderDto tickets;
    private Set<SeatDto> seats;
    private CustomerDto customer;


    public void validate(){

    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public TicketOrderDto getTickets() {
        return tickets;
    }

    public void setTickets(TicketOrderDto tickets) {
        this.tickets = tickets;
    }

    public Set<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(Set<SeatDto> seats) {
        this.seats = seats;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}