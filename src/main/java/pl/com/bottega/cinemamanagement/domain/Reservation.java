package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ulvar on 25.09.2016.
 */
@Entity
public class Reservation {

    @EmbeddedId
    private ReservationNumber number;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TicketOrder> ticketsOrder;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Seat> seats;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private Reservation() {
    }

    public Reservation(Set<TicketOrder> ticketsOrder, Set<Seat> seats, Customer customer) {
        this.ticketsOrder = ticketsOrder;
        this.seats = seats;
        this.customer = customer;
        this.number = new ReservationNumber();
        this.status = ReservationStatus.PENDING;
    }

    public ReservationNumber getNumber() {
        return number;
    }

    public Set<TicketOrder> getTicketsOrder() {
        return ticketsOrder;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
