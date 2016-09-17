package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemamanagement.domain.Movie;

import java.util.Collection;

/**
 * Created by arkadiuszarak on 05/09/2016.
 */
@Component
public class MovieFactory {

    public Movie create(String title, String description, Collection<String> actors, Collection<String> genres, Integer minAge, Integer length) {
        return new Movie(title, description, actors, genres, minAge, length);
    }
}
