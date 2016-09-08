package pl.com.bottega.cinemamanagement.domain;

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
    private Cinema cinema;

    @ManyToOne
    private Movie movie;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Show() {}

    public Show(Cinema cinema, Date date, Movie movie) {
        this.cinema = cinema;
        this.date = date;
        this.movie = movie;
    }
}
