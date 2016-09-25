package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

/**
 * Created by ulvar on 25.09.2016.
 */
@Entity
public class Reservation {

    @Id
    private Long Id;

    private ReservationNumber number;
    private Set<TicketOrder> ticketsOrder;
    private Set<Seat> seats;
    private Customer customer;
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
}
