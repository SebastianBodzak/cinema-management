package pl.com.bottega.cinemamanagement.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Show.showWithTicketPrices",
                query = "SELECT DISTINCT sh FROM Show sh JOIN FETCH sh.movie m JOIN FETCH m.ticketPrices tp WHERE sh.id = :showId"),
        @NamedQuery(name = "Show.findShowWithReservations",
                query = "SELECT DISTINCT sh FROM Show sh JOIN FETCH sh.movie m JOIN FETCH m.ticketPrices tp LEFT JOIN FETCH sh.reservations r WHERE sh.id = :showId")
})
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cinema cinema;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Movie movie;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Reservation> reservations;

    public Show() {
    }

    public Show(Cinema cinema, LocalDate date, Movie movie, LocalTime time) {
        this.cinema = cinema;
        this.date = date;
        this.movie = movie;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Show show = (Show) o;

        if (cinema != null ? !cinema.equals(show.cinema) : show.cinema != null) return false;
        if (movie != null ? !movie.equals(show.movie) : show.movie != null) return false;
        if (date != null ? !date.equals(show.date) : show.date != null) return false;
        return time != null ? time.equals(show.time) : show.time == null;

    }

    @Override
    public int hashCode() {
        int result = cinema != null ? cinema.hashCode() : 0;
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    public Set<TicketPrice> getTicketPrices() {
        return movie.getTicketPrices();
    }


}
