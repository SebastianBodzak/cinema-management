package pl.com.bottega.cinemamanagement.api;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by arkadiuszarak on 18/09/2016.
 */
public interface MovieCatalog {
    ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalDate date);
}
