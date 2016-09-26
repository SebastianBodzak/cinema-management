package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by ulvar on 25.09.2016.
 */
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long reservationNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TicketOrder> ticketsOrder;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Seat> seats;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private BigDecimal totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    private Show show;

    public Reservation() {
    }

    public Reservation(Show show, Set<TicketOrder> ticketsOrder, Set<Seat> seats, Customer customer, BigDecimal totalPrice) {
        this.ticketsOrder = ticketsOrder;
        this.seats = seats;
        this.customer = customer;
        this.status = ReservationStatus.PENDING;
        this.totalPrice = totalPrice;
        this.show = show;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Long getReservationNumber() {
        return reservationNumber;
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
