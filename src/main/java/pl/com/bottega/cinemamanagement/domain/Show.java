package pl.com.bottega.cinemamanagement.domain;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    private Show() {}

    public Show(Cinema cinema, Date date, Movie movie) {
        this.cinema = cinema;
        this.date = date;
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public Date getDate() {
        return date;
    }


}
