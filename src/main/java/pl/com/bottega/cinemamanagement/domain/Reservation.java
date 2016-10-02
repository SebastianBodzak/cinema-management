package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static pl.com.bottega.cinemamanagement.domain.ReservationStatus.*;

/**
 * Created by ulvar on 25.09.2016.
 */
@Entity
@NamedQueries({

        @NamedQuery(name = "Reservation.findReservation",
                query = "SELECT DISTINCT r FROM Reservation r JOIN FETCH r.customer c JOIN FETCH r.ticketsOrder to " +
                        "JOIN FETCH r.seats s JOIN FETCH r.show sh JOIN FETCH sh.movie m " +
                        "WHERE c.lastName =:lastName AND r.status =:status AND (sh.date >:date OR (sh.date =:date AND sh.time >= :time))"),
        @NamedQuery(name = "Reservation.findReservationByNumber",
                query = "SELECT DISTINCT r FROM Reservation r JOIN FETCH r.customer c JOIN FETCH r.ticketsOrder to " +
                        "JOIN FETCH r.seats s JOIN FETCH r.show sh JOIN FETCH sh.movie m " +
                        "WHERE r.reservationNumber =:reservationNumber")
})
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

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Payment> payments;

    public Reservation() {
    }

    public Reservation(Show show, Set<TicketOrder> ticketsOrder, Set<Seat> seats, Customer customer, BigDecimal totalPrice) {
        this.ticketsOrder = ticketsOrder;
        this.seats = seats;
        this.customer = customer;
        this.status = PENDING;
        this.totalPrice = totalPrice;
        this.show = show;
    }

    public void addPayment(Payment payment) {
        checkNotNull(payment);
        if (payment.isSuccesfull())
            status = PAID;
        else
            status = PAYMENT_FAILED;
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

    public Show getShow() {
        return show;
    }

    public LocalDate getShowDate() {
        return show.getDate();
    }

    public LocalTime getShowTime() {
        return show.getTime();
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber=" + reservationNumber +
                ", ticketsOrder=" + ticketsOrder +
                ", seats=" + seats +
                ", customer=" + customer +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", show=" + show +
                ", payments=" + payments +
                '}';
    }
}
