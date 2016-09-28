package pl.com.bottega.cinemamanagement.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Dell on 2016-09-28.
 */
public class CustomerReservationsDto {

    private Set<ReservationsDto> reservations;

    public class ReservationsDto {

        private String number;
        private ShowDto show;
        private MovieDto movie;
        private Set<TicketOrderDto> tickets;
        private Set<SeatDto> seats;
        private CustomerDto customer;
        private String status;
        private BigDecimal totalPrice;

        public class ShowDto {

            private Long id;
            private LocalDateTime dateTime;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public LocalDateTime getDateTime() {
                return dateTime;
            }

            public void setDateTime(LocalDateTime dateTime) {
                this.dateTime = dateTime;
            }
        }

        public class MovieDto {

            private Long id;
            private String title;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public class TicketOrderDto {

            private String kind;
            private Integer count;
            private BigDecimal unitPrice;
            private BigDecimal totalPrice;

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public Integer getCount() {
                return count;
            }

            public void setCount(Integer count) {
                this.count = count;
            }

            public BigDecimal getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(BigDecimal unitPrice) {
                this.unitPrice = unitPrice;
            }

            public BigDecimal getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(BigDecimal totalPrice) {
                this.totalPrice = totalPrice;
            }
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public ShowDto getShow() {
            return show;
        }

        public void setShow(ShowDto show) {
            this.show = show;
        }

        public MovieDto getMovie() {
            return movie;
        }

        public void setMovie(MovieDto movie) {
            this.movie = movie;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public BigDecimal getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    public Set<ReservationsDto> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationsDto> reservations) {
        this.reservations = reservations;
    }
}