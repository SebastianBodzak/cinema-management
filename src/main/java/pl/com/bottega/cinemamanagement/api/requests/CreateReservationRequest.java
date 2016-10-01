package pl.com.bottega.cinemamanagement.api.requests;

import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.dtos.CustomerDto;
import pl.com.bottega.cinemamanagement.api.dtos.SeatDto;
import pl.com.bottega.cinemamanagement.api.dtos.TicketOrderDto;

import java.util.Set;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class CreateReservationRequest {

    private ReservationDto reservation;

    public ReservationDto getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDto reservation) {
        this.reservation = reservation;
    }

    public String getFirstName() {
        return reservation.customer.getFirstName();
    }

    public String getLastName() {
        return reservation.customer.getLastName();
    }

    public String getEmail() {
        return reservation.customer.getEmail();
    }

    public String getPhone() {
        return reservation.customer.getPhone();
    }

    public void validate() {
        reservation.validate();
    }

    public Set<SeatDto> getSeats() {
        return reservation.seats;
    }

    public Long getShowId() {
        return reservation.showId;
    }

    public Set<TicketOrderDto> getTickets() {
        return reservation.tickets;
    }

    public class ReservationDto {

        private Long showId;
        private Set<TicketOrderDto> tickets;
        private Set<SeatDto> seats;
        private CustomerDto customer;

        public void validate() {
            if (showId == null)
                throw new InvalidRequestException("Missing show id");
            if (tickets == null || tickets.isEmpty())
                throw new InvalidRequestException("No tickets");
            if (seats == null || seats.isEmpty())
                throw new InvalidRequestException("No seats");
            if (customer == null)
                throw new InvalidRequestException("No customer");
            seats.stream().forEach(SeatDto::validate);
            tickets.stream().forEach(TicketOrderDto::validate);
            customer.validate();
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

}
