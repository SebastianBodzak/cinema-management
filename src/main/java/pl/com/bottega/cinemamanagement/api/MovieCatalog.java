package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.api.responses.ListMoviesInCinemaResponse;

import java.time.LocalDate;

/**
 * Created by arkadiuszarak on 18/09/2016.
 */
public interface MovieCatalog {
    ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalDate date);
}
