package pl.com.bottega.cinemamanagement.api.dtos;

import pl.com.bottega.cinemamanagement.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bartosz on 2016-09-30.
 */
public class ReservationsDto {

    private String number;
    private ShowDto show;
    private MovieDto movie;
    private Set<TicketOrderDto> tickets;
    private Set<SeatDto> seats;
    private CustomerDto customer;
    private String status;
    private BigDecimal totalPrice;

    public ReservationsDto(Reservation reservation) {
        this.number = reservation.getReservationNumber().toString();
        this.show = new ShowDto(reservation.getShow());
        this.movie = new MovieDto(reservation.getShow().getMovie());
        this.tickets = prepareSetOfTicketOrderDto(reservation.getTicketsOrder());
        this.seats = prepareSeatsDto(reservation.getSeats());
        this.customer = new CustomerDto(reservation.getCustomer());
        this.status = reservation.getStatus().toString();
        this.totalPrice = reservation.getTotalPrice();
    }

    private Set<TicketOrderDto> prepareSetOfTicketOrderDto(Set<TicketOrder> ticketsOrder) {
        Set<TicketOrderDto> tickets = new HashSet<>();
        for (TicketOrder ticketOrder : ticketsOrder) {
            tickets.add(new TicketOrderDto(ticketOrder));
        }
        return tickets;
    }

    private Set<SeatDto> prepareSeatsDto(Set<Seat> seats) {
        Set<SeatDto> seatDtos = new HashSet<>();
        for (Seat seat : seats) {
            seatDtos.add(new SeatDto(seat.getRow(), seat.getNumber()));
        }
        return seatDtos;
    }

    public String getNumber() {
        return number;
    }

    public ShowDto getShow() {
        return show;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public Set<TicketOrderDto> getTickets() {
        return tickets;
    }

    public Set<SeatDto> getSeats() {
        return seats;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public class ShowDto {

        private Long id;
        private LocalDateTime dateTime;

        public ShowDto(Show show) {
            this.id = show.getId();
            this.dateTime = LocalDateTime.of(show.getDate(), show.getTime());
        }

        public Long getId() {
            return id;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }
    }

    public class MovieDto {

        private Long id;
        private String title;

        public MovieDto(Movie movie) {
            this.id = movie.getId();
            this.title = movie.getTitle();
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }

    public class TicketOrderDto {

        private String kind;
        private Integer count;
        private BigDecimal unitPrice;
        private BigDecimal totalPrice;

        public TicketOrderDto(TicketOrder ticketsOrder) {
            this.kind = ticketsOrder.getKind();
            this.count = ticketsOrder.getCount();
            this.unitPrice = ticketsOrder.getUnitPrice();
            this.totalPrice = ticketsOrder.getTotalPrice();
        }

        public String getKind() {
            return kind;
        }

        public Integer getCount() {
            return count;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public BigDecimal getTotalPrice() {
            return totalPrice;
        }
    }

    public class CustomerDto {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;

        public CustomerDto(Customer customer) {
            this.id = customer.getId();
            this.firstName = customer.getFirstName();
            this.lastName = customer.getLastName();
            this.email = customer.getEmail();
            this.phone = customer.getPhone();
        }

        public Long getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }
    }
}
