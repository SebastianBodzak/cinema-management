package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.*;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Bartosz on 2016-09-27.
 */
public class ReservationFindDto {

    private Long reservationNumber;
    private Show show;
    private Movie movie;
    private Set<TicketOrder> ticketsOrder;
    private Set<Seat> seats;
    private Customer customer;
    private ReservationStatus status;
    private BigDecimal totalPrice;

    public ReservationFindDto(Long reservationNumber, Show show, Movie movie, Customer customer, ReservationStatus status) {
        this.reservationNumber = reservationNumber;
        this.show = show;
        this.movie = movie;
        this.customer = customer;
        this.status = status;
    }

    //    public ReservationFindDto(Long reservationNumber,Set<TicketOrder> ticketsOrder, Customer customer, ReservationStatus status) {
//        this.customer = customer;
//        this.ticketsOrder = ticketsOrder;
//        this.status = status;
//        this.reservationNumber = reservationNumber;
//    }

//        public ReservationFindDto(Long reservationNumber, Set<TicketOrder> ticketsOrder, Set<Seat> seats, Customer customer, ReservationStatus status, BigDecimal totalPrice) {
//        this.ticketsOrder = ticketsOrder;
//        this.seats = seats;
//        this.customer = customer;
//        this.status = status;
//        this.totalPrice = totalPrice;
//        this.reservationNumber = reservationNumber;
//    }

    //    public ReservationFindDto(Long reservationNumber, Show show, Movie movie, Set<TicketOrder> ticketsOrder,
//                                Set<Seat> seats, Customer customer, ReservationStatus status, BigDecimal totalPrice) {
//        this.reservationNumber = reservationNumber;
//        this.show = show;
//        this.movie = movie;
//        this.ticketsOrder = ticketsOrder;
//        this.seats = seats;
//        this.customer = customer;
//        this.status = status;
//        this.totalPrice = totalPrice;
//    }

    public Long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Set<TicketOrder> getTicketsOrder() {
        return ticketsOrder;
    }

    public void setTicketsOrder(Set<TicketOrder> ticketsOrder) {
        this.ticketsOrder = ticketsOrder;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationFindDto that = (ReservationFindDto) o;

        if (reservationNumber != null ? !reservationNumber.equals(that.reservationNumber) : that.reservationNumber != null)
            return false;
        if (show != null ? !show.equals(that.show) : that.show != null) return false;
        if (movie != null ? !movie.equals(that.movie) : that.movie != null) return false;
        if (ticketsOrder != null ? !ticketsOrder.equals(that.ticketsOrder) : that.ticketsOrder != null) return false;
        if (seats != null ? !seats.equals(that.seats) : that.seats != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (status != that.status) return false;
        return totalPrice != null ? totalPrice.equals(that.totalPrice) : that.totalPrice == null;

    }

    @Override
    public int hashCode() {
        int result = reservationNumber != null ? reservationNumber.hashCode() : 0;
        result = 31 * result + (show != null ? show.hashCode() : 0);
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (ticketsOrder != null ? ticketsOrder.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        return result;
    }
}
