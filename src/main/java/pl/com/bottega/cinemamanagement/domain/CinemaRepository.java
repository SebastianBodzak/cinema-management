package pl.com.bottega.cinemamanagement.domain;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
public interface CinemaRepository {
    void save(Cinema cinema);
    void load(String name, String city);
}
