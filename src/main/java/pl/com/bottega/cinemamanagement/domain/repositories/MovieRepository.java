package pl.com.bottega.cinemamanagement.domain.repositories;

import pl.com.bottega.cinemamanagement.domain.Movie;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
public interface MovieRepository {
    void save(Movie movie);

    Movie findById(Long id);
}
