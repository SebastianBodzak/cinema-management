package pl.com.bottega.cinemamanagement.domain;

import java.util.Collection;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
public interface MovieRepository {
    void save(Movie movie);

    Movie findById(Long id);
}
