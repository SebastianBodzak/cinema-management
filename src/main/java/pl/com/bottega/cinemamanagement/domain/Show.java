package pl.com.bottega.cinemamanagement.domain;

import com.sun.istack.internal.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    @NotNull
    private Cinema cinema;

    @ManyToOne
    @NotNull
    private Movie movie;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull
    private  LocalTime time;

    private Show() {}

    public Show(Cinema cinema, LocalDate date, Movie movie, LocalTime time) {
        this.cinema = cinema;
        this.date = date;
        this.movie = movie;
        this.time = time;
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
}
