package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemamanagement.domain.Movie;

import java.util.Collection;

/**
 * Created by arkadiuszarak on 05/09/2016.
 */
@Component
public class MovieFactory {

    public Movie create(CreateMovieRequest request) {
        return new Movie(request.getMovie().getTitle(),
                request.getMovie().getDescription(),
                request.getMovie().getActors(),
                request.getMovie().getGenres(),
                request.getMovie().getMinAge(),
                request.getMovie().getLength());
    }
}
