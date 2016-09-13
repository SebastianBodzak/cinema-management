package pl.com.bottega.cinemamanagement.domain;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
public interface CinemaRepository {

    void save(Cinema cinema);

    Cinema load(String name, String city);

    Cinema findById(Long cinemaId);
}
