package pl.com.bottega.cinemamanagement.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
@Entity
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cinema cinema;

    @ManyToOne
    private Movie movie;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;

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
}
