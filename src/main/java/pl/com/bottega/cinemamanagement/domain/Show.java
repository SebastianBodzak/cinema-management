package pl.com.bottega.cinemamanagement.domain;

import java.util.Date;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
public class Show {

    private Cinema cinema;
    private Movie movie;
    private Date date;

    public Show(Cinema cinema, Date date, Movie movie) {
        this.cinema = cinema;
        this.date = date;
        this.movie = movie;
    }
}