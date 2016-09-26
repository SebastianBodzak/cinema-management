package pl.com.bottega.cinemamanagement.domain.repositories;

import pl.com.bottega.cinemamanagement.domain.Cinema;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
public interface CinemaRepository {

    void save(Cinema cinema);

    Cinema load(String name, String city);

    Cinema findById(Long cinemaId);
}
